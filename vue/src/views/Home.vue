<template>
  <div id="home-page-display">
    <SideBar id="side-bar"></SideBar>

    <ImageColumn class="image-column"></ImageColumn>
  </div>
</template>

<script>
import SideBar from "@/components/SideBar.vue";
import ImageColumn from "@/components/ImageColumn.vue";
import accountService from "@/services/AccountService.js";

export default {
  name: "home",
  data() {
    return {
      defaultAccount: {
        accountId: -1,
        userId: -1,
        displayName: "",
      },
    };
  },

  components: {
    SideBar,
    ImageColumn,
  },
  created() {
    this.$store.commit("SET_ACCOUNT", this.defaultAccount);
    accountService.getAccountSelf().then((response) => {
      this.$store.commit("SET_CURRENT_ACCOUNT", response.data);
    });
  },
};
</script>

<style>
#home-page-display {
  max-width: (100vw);
  /* align-items: space-between; */
  /* justify-content: space-between; */
}

#side-bar {
  width: 275px;
  margin: 0px 0px 0px 0px;
  height: 100vh;
  position: fixed;
}

.image-column {
  width: calc(100% - 315px);
  margin-left: 295px;
  margin-right: 20px;
  padding-top: 20px;
}
</style>