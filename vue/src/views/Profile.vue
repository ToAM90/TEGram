<template>
  <div id="home">
    <profile-header id="profile-header"></profile-header>
    <create-post
      v-if="
        $store.state.account.accountId === $store.state.currentAccount.accountId
      "
    />
    <image-column class="profile-image-column" />
  </div>
</template>

<script>
import ProfileHeader from "@/components/ProfileHeader.vue";
import ImageColumn from "../components/ImageColumn.vue";
import PostService from "@/services/PostService.js";
import AccountService from "@/services/AccountService.js";
import CreatePost from "@/components/CreatePost.vue";

export default {
  name: "profile",
  components: {
    ProfileHeader,
    ImageColumn,
    CreatePost,
  },
  data() {
    return {
      // hideHeader: false,
      // scrollDistance: 0,
    };
  },
  created() {
    AccountService.getAccountOther(this.$route.params.id).then((response) => {
      this.$store.commit("SET_ACCOUNT", response.data);
      console.log(this.$store.state.account);
    });
    PostService.getAccountPosts(this.$route.params.id).then((response) => {
      this.$store.commit("INITIALIZE_POSTS", response.data);
    });
    this.$store.commit("CHANGE_CURRENT_VIEW", "profile");
    // window.addEventListener("scroll", this.calcHideHeader);
  },
  methods: {
    // calcHideHeader() {
    //   console.log(window.scrollY);
    //   this.hideHeader = true;
    //   console.log("test");
    //   if (this.scrollDistance > window.scrollY) {
    //     this.hideHeader = true;
    //   } else {
    //     this.hideHeader = false;
    //   }
    //   this.scrollDistance = window.scrollY;
    // },
  },
};
</script>

<style>
#home {
  max-width: 100vw;
  display: flex;
  flex-direction: column;
  align-items: center;
}

#profile-header {
  /* width: 100vw; */
  position: fixed;
}

.page-title {
  padding-top: 175px;
  text-align: center;
  color: black;
}

.profile-image-column {
  padding-top: 200px;
  margin-left: 20px;
  margin-right: 20px;
}
</style>