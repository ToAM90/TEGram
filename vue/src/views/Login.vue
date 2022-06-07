<template>
  <div id="login" class="login">
    <form class="form-signin" @submit.prevent="login">
      <h1 id="sign-in-display" class="splash-logo">.TE GRAM</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <label for="username" class="sr-only"></label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />

      <label for="password" class="sr-only"></label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />

      <div class="login-buttons">
        <button
          id="login-button"
          class="button"
          type="submit"
          :to="{ name: 'register' }"
        >
          Sign in
        </button>
        <p id="or">__________ or ___________</p>
        <button
          id="need-an-account-button"
          class="button"
          type="routePlaceholder"
          v-on:click.prevent="pushToRegister"
        >
          Need an account?
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import accountService from "../services/AccountService";

export default {
  name: "login",
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            accountService.getAccountSelf().then((response) => {
              this.$store.commit("SET_CURRENT_ACCOUNT", response.data);
            });
            this.$router.push("/");
          }
          // this.$router.push("/");
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });

      // this.$router.push("/");
    },
    pushToRegister() {
      this.$router.push("/register");
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Passion+One&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Comfortaa:wght@300&family=Passion+One&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Comfortaa:wght@300&family=Montserrat:wght@300&family=Passion+One&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Montserrat:wght@800&display=swap");
.login {
  height: 100vh;
  padding-top: auto;
}

#sign-in-display {
  font-family: "Montserrat", sans-serif;
  font-size: 5rem !important;
  text-align: center;
  color: var(--sign-in-salmon);
  margin: 20px;
}

.login {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  max-height: 100%;
  font-family: "Open Sans", sans-serif;
}

.form-signin {
  width: 40%;
  max-height: 100%;
  max-width: 380px;
  display: flex;
  flex-direction: column;
  border: 16px solid var(--sign-in-salmon);
  border-radius: 4px;
  padding: 20px;
  border-end-end-radius: 48px;
  border-start-start-radius: 48px;
  background-color: var(--panel-background-color);
  box-shadow: 3px 3px 20px var(--panel-background-color),
    6px 6px 32px var(--panel-background-color);
}

.form-control {
  padding: 8px 4px;

  margin: 4px 0px;
  border: 1px solid grey;
  border-radius: 5px;

  color: #2a2d34;
  font-size: 1.1rem;
}

#sign-in-display {
  font-size: 2rem;
}

#or {
  text-align: center;

  flex-grow: 10000;
  margin: 16px;
  color: var(--sign-in-salmon);
}

.button {
  font-family: "Open Sans", sans-serif;
  font-weight: 500;
  width: 100%;
  max-height: 32px;
  margin: 0px;

  padding: 5px;
}

.login-buttons {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0px;
  margin: 0px;
}

button:hover {
  background-color: var(--panel-background-color);
  font-size: 0.9rem;
  transition-duration: 0.2s;
}

button {
  border: 0px;
}

#login-button {
  margin-top: 20px;
  background-color: var(--sign-in-salmon);
  border-radius: 5px;
  color: var(--panel-background-color);

  margin: 4px;
}

.login-buttons {
  display: flex;
  flex-direction: column;
  justify-items: center;
}

#need-an-account-button {
  background-color: var(--sign-up-blue);
  color: var(--panel-background-color);
  width: 60%;
  border-radius: 5px;
  margin: 0px;
}

.po-password-strength-bar {
  border-radius: 2px;
  transition: all 0.2s linear;
  height: 5px;
  margin-top: 8px;
}

.po-password-strength-bar.risky {
  background-color: #f95e68;
}

.po-password-strength-bar.guessable {
  background-color: #fb964d;
}

.po-password-strength-bar.weak {
  background-color: #fdd244;
}

.po-password-strength-bar.safe {
  background-color: #b0dc53;
}

.po-password-strength-bar.secure {
  background-color: #35cc62;
}
</style>
