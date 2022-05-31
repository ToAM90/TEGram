<template>

  <div id="login" class="text-center">
    <h1 id="sign-in-display" class="h3 mb-3 font-weight-normal">Please Sign In</h1>
    <form class="form-signin" @submit.prevent="login">
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
      <div class='login-buttons'>
        <button id='need-an-account-button' class='login-button' type="routePlaceholder">Need an account?</button>
        <button id='login-button' class='login-button' type="submit">Sign in</button>
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: { },
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap');
#login {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-family: 'Open Sans', sans-serif;
}

.form-signin {
  width: 400px;
  display: flex;
  flex-direction: column;
  border: 1px solid grey;
  border-radius: 10px;
  padding: 20px;
  background-color: #8A8E91;
}

.sr-only {
  margin: 5px;
}

#sign-in-display {
  font-size: 2REM
}

.login-button {
  padding: 5px;
  
  margin: 5px;
  
  font-family: 'Open Sans', sans-serif;
  font-weight: 500;
  width: 50%
}

.login-buttons { 
  display: flex;
  flex-direction: row;
}

button {
  border: 0px
}

button :hover{
  color: red;
}
#login-button {
  
  border: 2px solid green;
  
  border-radius: 10px;
}

#need-an-account-button {
  
  border: 2px solid purple;
  
  border-radius: 10px;
}

</style>
