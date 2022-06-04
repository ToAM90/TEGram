<template>
  <div id="home-page-display">
    <SideBar id="side-bar-home"></SideBar>

    <ImageColumn class="image-column"></ImageColumn>
  </div>
</template>

<script>
import SideBar from "@/components/SideBar.vue";
import ImageColumn from "@/components/ImageColumn.vue";
import postService from "@/services/PostService.js";

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

  created() {
    console.log(this.$store.state.posts);
    console.log(this.$store.state.currentAccount);
    postService.getAllPosts().then((response) => {
      this.$store.commit("INITIALIZE_POSTS", response.data);
    });
  },

  components: {
    SideBar,
    ImageColumn,
  },
};
</script>

<style>
#home-page-display {
  width: (100vw);
  overflow-x: hidden;
}

#side-bar-home {
  width: 275px;
  height: calc(100vh);
  position: fixed;
}

.image-column {
  width: calc(100% - 315px);
  margin-left: 295px;
  margin-right: auto;
  padding-top: 20px;
}
</style>