<template>
  <div id="home">
    <profile-header id="profile-header"></profile-header>
    <div id="profile-header-2">
      <create-post
        v-if="
          $store.state.account.accountId ===
          $store.state.currentAccount.accountId
        "
        id="profile-create-post"
      />
      <div class="profile-bio">
        <p>{{ $store.state.account.biography }}</p>
      </div>
    </div>
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
  z-index: 9;
}

#profile-header-2 {
  top: 150px;
  position: relative;
  display: flex;
  flex-direction: row;
  align-content: center;
  justify-content: center;
  height: 205px;
}

#profile-create-post {
  height: 200px;
  /* padding-bottom: 30px;*/
  padding-top: 20px;
  left: 75%;
  bottom: 75%;
  /* background-color: #d1d1d3; */
  /* border-top: 2px #d1d1d3 solid;
  border-bottom: 2px #d1d1d3 solid; */

  /* border-radius: 10px; */
}
#profile-create-post:after {
  content: "";
  display: block;
  margin: 0 auto;
  width: 50%;
  padding-top: 20px;
  border-bottom: 2px #d1d1d3 solid;
}
#profile-create-post:before {
  content: "";
  display: block;
  margin: 0 auto;
  width: 50%;
  padding-top: 20px;
  border-top: 2px #d1d1d3 solid;
}

#profile-bio {
  height: 175px;
  padding: 10px;
  /* padding-top: 20px; */

  background-color: #d1d1d3;
  border: 5px rgba(0, 0, 0, 0.308) solid;

  border-radius: 10px;
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