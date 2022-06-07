<template>
  <div class="post-header-bar">
    <router-link
      class="display-name"
      v-bind:to="{ name: 'profile', params: { id: accountId } }"
    >
      <p class="display-name">{{ getDisplayName }}</p>
    </router-link>
  </div>
</template>

<script>
export default {
  name: "post-header",
  props: { accountId: Number },
  // data() {
  //   return {
  //     account: {
  //       accountId: 0,
  //       displayName: "",
  //     },
  //   };
  // },
  computed: {
    getDisplayName() {
      return this.$store.state.accounts.find((stateAccount) => {
        console.log("this is a display name:" + stateAccount.displayName);
        if (stateAccount.accountId == this.accountId) return stateAccount;
      }).displayName;
    },
  },
};
</script>

<style>
.post-header-bar {
  background-image: url("https://res.cloudinary.com/dcipg5scy/image/upload/v1654570644/TE_GRAM/u7k1krgvbdjhnfujrwhy.png");
  box-shadow: 0px 0px 5px rgb(172, 169, 169);
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  height: 20px;
  border-radius: 4px 4px 0px 0px;
  padding-top: 5px;
  padding-bottom: 5px;
}

.display-name {
  margin: auto;
  /* padding: 8px; */

  color: #ffd084;

  font-size: 1.3rem;
  font-family: "Montserrat", sans-serif;
  font-weight: 600;
}
</style>