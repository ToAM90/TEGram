<template>
  <div id="home">
    <profile-header id="profile-header"></profile-header>
    <image-column class="profile-image-column" />
  </div>
</template>

<script>
import ProfileHeader from "@/components/ProfileHeader.vue";
import ImageColumn from "../components/ImageColumn.vue";
import postService from "@/services/PostService.js";

export default {
  name: "profile",

  components: {
    ProfileHeader,
    ImageColumn,
  },
  created() {
    console.log(this.$store.state.posts);
    console.log(this.$store.state.currentAccount);

    postService
      .getAccountPosts(this.$store.state.account.accountId)
      .then((response) => {
        this.$store.commit("INITIALIZE_POSTS", response.data);
      });
  },
};
</script>

<style>
#home {
  max-width: 100vw;
  display: flex;
  flex-direction: column;
}

#profile-header {
  /* width: 100vw; */
  position: fixed;
}

.profile-image-column {
  padding-top: 150px;
  margin-left: 20px;

  margin-right: 20px;
}
</style>