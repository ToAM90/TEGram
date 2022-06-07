<template>
  <div id="register" class="register">
    <div class="profile-update">
      <form class="form-register" v-on:submit.prevent="updateAccount">
          <div class="inputs">
          <h1 id="create-account-banner" class="h3 mb-3 font-weight-normal">
              UPDATE PROFILE
              </h1>
        <label for="displayName" class="sr-only">
          Display Name:</label>
        <input id="displayName" type="text" v-model="newAccount.displayName" />
        <label for="biography" class="sr-only"> About Me:</label>
        <input id="biography" type="text" v-model="newAccount.biography" />
          </div>
        <div class="update-buttons">
          <button
            id="upload_widget"
            class="button"
            @click.prevent="uploadPhoto">
            Upload Profile Image
          </button>
          <button v-on:click.prevent="resetForm" type="cancel" class="button">Cancel</button>
          <button class="button">Submit</button>
        </div>
      </form>
    </div>
<!--
    <div class="password-update">
      <form v-on:submit.prevent="updatePassword">
        <div
          class="alert alert-danger"
          role="alert"
          v-if="passwordChangeErrors"
        >
          {{ passwordChangeErrorMsg }}
        </div>
        <label for="password" class="sr-only"></label>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="newUser.password"
          required
        />
        <password-meter :password="newUser.password" />
        <label for="confirmPassword"></label>
        <input
          type="password"
          id="confirmPassword"
          class="form-control"
          placeholder="Confirm Password"
          v-model="newUser.confirmPassword"
          required
        />
        <div class="update-buttons">
          <button v-on:click.prevent="resetForm" type="cancel">Cancel</button>
          <button type="submit">Submit</button>
        </div>
      </form>
    </div> -->
  </div>
</template>
<script>
import AccountService from "../services/AccountService";
import AuthService from "../services/AuthService";
// import PasswordMeter from "vue-simple-password-meter";
export default {
  name: "update",
//   components: { PasswordMeter },
  data() {
    return {
      newAccount: {},
      newUser: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      passwordChangeErrors: false,
      passwordChangeErrorMsg: "There were problems changing password",
      passwordStrengthMsg: "",
    };
  },
  methods: {
    updateAccount() {
      AccountService.updateAccount(this.newAccount);
      this.$router.push({ name: "home" });
    },
    resetForm() {
      AccountService.getAccountSelf().then((response) => {
        this.newAccount = response.data;
        this.$router.push({ name: "home" });
      });
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
                path: `/profile/${this.$store.state.currentAccount.accountId}`,
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
              this.imageUrl = result.info.url;
            } else {
              console.log(error);
            }
          }
        )
        .open();
    },
  },
  created() {
    AccountService.getAccountSelf().then((response) => {
      this.newAccount = response.data;
    });
  },
};
</script>
<style>
</style>
