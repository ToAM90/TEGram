<template>
  <div id="app">
    <router-view id="view" />
  </div>
</template>

<script>
import accountService from "@/services/AccountService.js";

export default {
  name: "app",
  data() {
    return {
      defaultAccount: {
        accountId: -1,
        userId: -1,
        displayName: "",
      },
    };
  },
  created() {
    this.$store.commit("SET_ACCOUNT", this.defaultAccount);
    accountService.getAccountSelf().then((response) => {
      this.$store.commit("SET_CURRENT_ACCOUNT", response.data);
      // console.log(this.$store.state.currentAccount);
    });
  },
};
</script>

<style>
:root {
  --top-color: #38413e;
  --primary-background-color: #c8cfd6;
  --panel-background-color: #f1fffa;
  --sign-up-blue: #5096b9;
  --sign-in-salmon: #fe9aa4;
}

@import url("https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap");

#view {
  color: var(--panel-background-color);
  text-decoration: none;
  font-family: "Open Sans", sans-serif;
  max-height: 100vh;
  max-width: 100vw;
}
#app {

  min-height: 100vh;
  overflow: auto;
  width: 100vw;
  background: rgb(35, 33, 37);
  margin: -8px;
  padding: 0px;
}
</style>
