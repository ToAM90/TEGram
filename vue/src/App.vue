<template>
  <div id="app">
    <!-- <div id="nav">
      <router-link id="home" v-bind:to="{ name: 'home' }">Home |</router-link>
      
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div> -->
    <router-view />
  </div>
</template>

<style>
:root {
  --top-color: #38413e; ;
  --primary-background-color: #c8cfd6 ;
  --panel-background-color: #f1fffa ;
  --sign-up-blue: #5096b9 ;
  --sign-in-salmon: #fe9aa4 ;
}

@import url("https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap");
#nav {
  margin: 0px;
  padding: 10px;
  background-color: var(--top-color);
  border-bottom: 4px solid var(--panel-background-color);
  height: 40px;
  justify-content: center;
}
#home {
  color: var(--panel-background-color);
  text-decoration: none;
  font-family: "Open Sans", sans-serif;
}
#app {
  margin: -8px;
  padding: 0px;
  background-color: var(--primary-background-color);
  height: 100vh;
  width: 100vw;
}
</style>
