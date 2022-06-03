<template>
  <div id="register" class="register">
    <form class="form-register" @submit.prevent="register">
      <h1 id="create-account-banner" class="h3 mb-3 font-weight-normal">
        .TE GRAM
      </h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="inputs">
        <label for="username" class="sr-only"></label>
        <input
          type="text"
          id="username"
          class="form-control register-form"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <label for="password" class="sr-only"></label>
        <input
          type="password"
          id="password"
          class="form-control register-form"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <password-meter :password="user.password" />
        <input
          type="password"
          id="confirmPassword"
          class="form-control register-form"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />
      </div>
      <div class="register-buttons">
        <button
          id="create-button"
          class="button"
          type="submit"
          v-on:click.prevent="register"
        >
          Create Account
        </button>
        <p id="register-or">____________ or ____________</p>
        <button
          id="have-an-account-button"
          class="button"
          type="routePlaceholder"
          v-on:click.prevent="pushToLogin"
        >
          Have an account?
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import passwordMeter from "vue-simple-password-meter";

export default {
  name: "register",
  components: { passwordMeter },
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg =
                "There were problems registering this user.";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
    pushToLogin() {
      this.$router.push("/");
    },
    onScore({ score, strength }) {
      console.log(score); // from 0 to 4
      console.log(strength); // one of : 'risky', 'guessable', 'weak', 'safe' , 'secure'
      this.score = score;
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Passion+One&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Comfortaa:wght@300&family=Montserrat:wght@300&family=Passion+One&display=swap");

#create-account-banner {
  font-family: "Montserrat", sans-serif;
  font-size: 5rem !important;
  text-align: center;
  color: var(--sign-up-blue);
  margin: 20px;
}

.register {
  align-items: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  max-height: 100%;
  font-family: "Open Sans", sans-serif;
}

.form-register {
  width: 40%;
  max-width: 380px;
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
}

.alert {
  color: red;
  text-align: center;
}

.inputs {
  display: flex;
  flex-direction: column;
  align-items: center;

  margin: 5px;
  color: #2a2d34;
  font-size: 1.3rem;
}

.register-form {
  width: 100%;
  margin: 4px 0px;
  padding: 8px 4px;
}

.create-button {
  padding: 5px;

  margin: 5px;

  font-family: "Open Sans", sans-serif;
  font-weight: 500;
  width: 100%;
  max-height: 32px;
}
#register-or {
  color: var(--sign-up-blue);
}

.register-buttons {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

button:hover {
  background-color: var(--panel-background-color);
  box-shadow: 3px 5px 5px rgba(0, 0, 0, 0.2), 3px 6px 5px rgba(0, 0, 0, 0.19);
  transition-duration: 0.2s;
}

#create-button {
  background-color: var(--sign-up-blue);
  border-radius: 5px;
  color: var(--panel-background-color);
}

#have-an-account-button {
  background-color: var(--sign-in-salmon);
  color: var(--panel-background-color);
  width: 60%;
  border-radius: 5px;
}
</style>
