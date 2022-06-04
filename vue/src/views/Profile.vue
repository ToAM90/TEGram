<template>
  <div id="home">
    <profile-header id="profile-header"></profile-header>
    <image-column class="profile-image-column" />
    <create-post v-if="this.$store.state.currentAccount.accountId == this.$store.state.account.accountId"/>
   
  </div>
</template>

<script>
import ProfileHeader from "@/components/ProfileHeader.vue";
import ImageColumn from "../components/ImageColumn.vue";
import postService from "@/services/PostService.js";

import accountService from "@/services/AccountService.js";
import CreatePost from '../components/CreatePost.vue';

export default {
  name: "profile",
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
    ProfileHeader,
    ImageColumn,
    CreatePost,
  },
  created() {
    console.log(this.$store.state.posts);
    console.log(this.$store.state.currentAccount);

    postService
      .getAccountPosts(this.$store.state.account.accountId)
      .then((response) => {
        this.$store.commit("INITIALIZE_POSTS", response.data);
      });

    this.$store.commit("SET_ACCOUNT", this.defaultAccount);
    accountService.getAccountSelf().then((response) => {
      this.$store.commit("SET_CURRENT_ACCOUNT", response.data);
    });

    this.$store.commit("CHANGE_CURRENT_VIEW", "profile");
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