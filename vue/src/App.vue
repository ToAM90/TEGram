<template>
  <div id="app">
    <div id="nav">
      <router-link id="home" v-bind:to="{ name: 'home' }">Home |</router-link>
      
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
    <router-view />
  </div>
</template>

<style>
@import url("https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap");
#nav {
  margin: 0px;
  padding: 10px;
  background-color: #2a2d34;
  border-bottom: 4px solid #f1fffa;
}
#home {
  color: #f1fffa;
  text-decoration: none;
  font-family: "Open Sans", sans-serif;
}
#app {
  margin: -8px;
  padding: 0px;
  background-color: #8a8e91;
  color: #b8d4e3;
  height: 100vh;
  width: 100vw;
}
</style>
