<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<style>
#nav {

  margin: 0px;
  padding: 10px;
  background-color: #8A8E91;
  border-bottom: 4px solid #B8D4E3;
}
#app {
  margin: -8px;
  padding: 0px;
  background-color: #2A2D34;
  color: #B8D4E3;
  height: 100vh;
  width: 100vw;
}
</style>
