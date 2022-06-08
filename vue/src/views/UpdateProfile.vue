<template>
  <div id="update" class="update">
    <div class="form-update">
      <h1 id="update-account-banner" class="h3 mb-3 font-weight-normal">
        UPDATE PROFILE
      </h1>
      <div class="profile-update" v-if="showUpdateProfileForm">
        <form v-on:submit.prevent="updateAccount">
          <div class="inputs">
            <div @click="uploadPhoto">
              <img
                :src="newAccount.profileImg"
                alt=""
                id="update-profile-icon"
              />
              <img class="edit-button" src="@/assets/edit-97.png" />
            </div>
            <div class="update-message" v-if="successful">
                Successfully Updated
            </div>
            <label for="display-name" class="sr-only"> Display Name:</label>
            <input
              id="display-name"
              type="text"
              class="form-control register-form"
              v-model="newAccount.displayName"
            />
            <label for="biography" class="sr-only"> About Me:</label>
            <textarea
              id="biography"
              v-model="newAccount.biography"
              class="form-control register-form"
            />
          </div>

          <div class="update-buttons">
            <button
              class="button update-button"
              v-on:click.prevent="cancelForm"
              type="cancel"
            >
              Cancel
            </button>
            <button class="button update-button">Submit</button>
          </div>
        </form>
      </div>

      <div class="password-update" v-if="!showUpdateProfileForm">
        <form v-on:submit.prevent="updatePassword">
           <div class="inputs">
          <div
            class="alert alert-danger"
            role="alert"
            v-if="passwordChangeErrors"
          >
            {{ passwordChangeErrorMsg }}
          </div>
          <label for="password" class="sr-only">Change Password:</label>
          <input
            type="password"
            id="password"
            placeholder="Password"
            v-model="newUser.password"
            class="form-control register-form"
            required
          />
          <password-meter :password="newUser.password" />
          <label for="confirmPassword"></label>
          <input
            type="password"
            id="confirmPassword"
            placeholder="Confirm Password"
            v-model="newUser.confirmPassword"
            class="form-control register-form"
            required
          /> </div>
          <div class="update-buttons">
            <button
              class="button update-button"
              v-on:click.prevent="cancelForm"
              type="cancel"
            >
              Cancel
            </button>
            <button class="button update-button" type="submit">
              Submit
            </button>
          </div>
        </form>
      </div>
      <button
        id="change-form"
        class="button"
        v-on:click="showUpdateProfileForm = !showUpdateProfileForm"
      >
        Click to
        {{
          showUpdateProfileForm
            ? "Change Password"
            : "Change Profile Information"
        }}
      </button>
    </div>
  </div>
</template>
<script>
import AuthService from "../services/AuthService";
import PasswordMeter from "vue-simple-password-meter";
import AccountService from "../services/AccountService";
export default {
  name: "update",
  components: { PasswordMeter },
  data() {
    return {
      newAccount: {},
      newUser: {},
      passwordChangeErrors: false,
      passwordChangeErrorMsg: "There were problems changing password",
      passwordStrengthMsg: "",
      showUpdateProfileForm: true,
      successful: false,
    };
  },
  computed: {
    getProfilePic() {
      console.log(this.$store.state.currentAccount.profileImg);
      return this.$store.state.currentAccount.profileImg;
    },
  },
  methods: {
    updateAccount() {
      AccountService.updateAccount(this.newAccount);
      AccountService.getAccountSelf().then((response) => {
        this.$store.commit("SET_CURRENT_ACCOUNT", response.data);
        if(response.status == 200){
            this.successful = true;
        }
      });
    },
    cancelForm() {
      this.$router.push({ name: "home" });
    },
    updatePassword() {
      if (this.newUser.password != this.newUser.confirmPassword) {
        this.passwordChangeErrors = true;
        this.passwordChangeErrorMsg =
          "Password and Confirm Password do not match.";
      } else if (this.score < 3) {
        this.passwordChangeErrors = true;
        this.passwordChangeErrorMsg = "Password is not strong enough.";
      } else {
        AuthService.update(this.newUser)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: `{/}`,
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.passwordChangeErrors = true;
            if (response.status == 400) {
              this.passwordChangeErrorMsg =
                "There were problems with changing your password";
            }
          });
      }
    },
    onScore({ score, strength }) {
      this.score = score;
      this.passwordStrengthMsg = strength;
    },
    uploadPhoto() {
      window.cloudinary
        .openUploadWidget(
          {
            cloud_name: "dcipg5scy",
            upload_preset: "TE-GRAM",
            maxFiles: 1,
          },
          (error, result) => {
            if (!error && result && result.event === "success") {
              console.log(result.info.url);
              this.newAccount.profileImg = result.info.url;
              AccountService.updateAccount(this.newAccount);
              this.$router.go(0);
            } else {
              console.log(error);
            }
          }
        )
        .open();
    },
  },
  created() {
    AuthService.getUser().then((response) => {
      this.newUser = response.data;
    });
    AccountService.getAccountSelf().then((response) => {
      this.newAccount = response.data;
    });
  },
};
</script>
<style>
@import url("https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Passion+One&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Comfortaa:wght@300&family=Montserrat:wght@300&family=Passion+One&display=swap");

#update-account-banner {
  font-family: "Montserrat", sans-serif;
  font-size: 5rem !important;
  text-align: center;
  color: var(--sign-up-blue);
  margin: 20px;
}

.update {
  align-items: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  font-family: "Open Sans", sans-serif;
  height: 100vh;
  padding-top: auto;
}

.form-update {
  width: 100%;
  height: 750px;
  max-width: 500px;
  max-height: 100%;
  display: flex;
  flex-direction: column;
  border: 1px solid grey;
  border-radius: 10px;
  padding: 20px;
  background-color: var(--panel-background-color);
  border: 16px solid var(--sign-up-blue);
  border-radius: 4px;
  border-end-end-radius: 48px;
  border-start-start-radius: 48px;
  box-shadow: 3px 3px 20px var(--panel-background-color),
    6px 6px 32px var(--panel-background-color);
  justify-content: center;
}

.password-update {
  margin-top: 80px;
  text-align: center;
}

.update-form {
  width: 1000%;
  margin: 4px 0px;
  padding: 8px 4px;
}

.update-buttons {
  margin: auto;
  display: inline-block;
  font-family: "Open Sans", sans-serif;
  font-weight: 500;
  width: 100%;
  max-height: 200px;
  background-color: var(--sign-up-blue);
  border-radius: 5px;
  color: var(--panel-background-color);
  display: flex;
}

.button:hover {
  background-color: var(--panel-background-color);
  box-shadow: 3px 5px 5px rgba(0, 0, 0, 0.2), 3px 6px 5px rgba(0, 0, 0, 0.19);
  transition-duration: 0.2s;
}

.update-button {
  margin: auto;
  display: inline-block;
  background-color: var(--sign-up-blue);
  border-radius: 5px;
  color: var(--panel-background-color);
}

#update-profile-icon {
  margin-top: 35px;
  height: 200px;
  width: 200px;
  padding: 0px;
  box-shadow: 6px 6px 25px rgb(156, 155, 155);
  border-radius: 100%;
  cursor: pointer;
  margin-bottom: 50px;
  margin-top: -15px;
}

.edit-button {
  max-width: 30px;
  margin-left: -25px;
  cursor: pointer;
  margin-bottom: 50px;
}

#change-form {
  background-color: var(--sign-in-salmon);
  color: var(--panel-background-color);
  width: 60%;
  border-radius: 5px;
}
.update-message{
  margin-top: -30px;

  padding-bottom: 10px;
  color: salmon;
}
</style>