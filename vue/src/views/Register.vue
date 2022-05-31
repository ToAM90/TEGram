<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 id="create-account-banner" class="h3 mb-3 font-weight-normal">
        .TE-GRAM
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
          <button id="create-button" class="button" type="submit">
            Create Account
          </button>
          <p id="or">____________ or ____________</p>
          <button
            id="need-an-account-button"
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

export default {
  name: "register",
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
              this.registrationErrorMsg = "Bad Request: Validation Errors";
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
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Passion+One&display=swap");

#create-account-banner {
  font-family: "Passion One", cursive;
  font-size: 5rem !important;
  text-align: center;
  color: #fe9aa4;

  margin: 20px;
}

#register {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 10%;
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
  background-color: #f1fffa;
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

.register-buttons {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

button:hover {
  background-color: #f1fffa;
  font-size: 0.9rem;
  transition-duration: 0.2s;
}



#create-button {
  background-color: #5096b9;
  border-radius: 5px;
  color: #f1fffa;
}

#need-an-account-button {
  background-color: #ee5044;
  color: #f1fffa;
  width: 60%;
  border-radius: 5px;
}
</style>
