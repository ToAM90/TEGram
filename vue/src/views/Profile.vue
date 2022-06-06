<template>
  <div id="home">
    <profile-header id="profile-header"></profile-header>
    <div class="page-title">{{this.$store.state.account.displayName}}</div>
    <image-column class="profile-image-column" />
    <create-post v-if="this.$store.state.currentAccount.accountId == this.$store.state.account.accountId"/>
   
  </div>
</template>

<script>
import ProfileHeader from "@/components/ProfileHeader.vue";
import ImageColumn from "../components/ImageColumn.vue";
import PostService from "@/services/PostService.js";
import AccountService from "@/services/AccountService.js";
import CreatePost from '../components/CreatePost.vue';

export default {
  name: "profile",
  components: {
    ProfileHeader,
    ImageColumn,
    CreatePost,
  },
  created() {
    AccountService.getAccountOther(this.$route.params.id).then((response) => 
        {this.$store.commit("SET_ACCOUNT", response.data)});
    PostService.getAccountPosts(this.$route.params.id)
      .then((response) => {
        this.$store.commit("INITIALIZE_POSTS", response.data);});
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

.page-title{
    padding-top: 200px;
     text-align:center;
     color: black;
}

.profile-image-column {
  padding-top: 50px;
  margin-left: 20px;
  margin-right: 20px;
}
</style>