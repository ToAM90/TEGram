<template>
  <div id="home">
    <profile-header id="profile-header"></profile-header>

    <div id="profile-header-2">
      <div id="bio-create-box">
        <div
          class="profile-bio"
          v-if="$store.state.account.biography !== null && showBio === true"
        >
          <p>{{ $store.state.account.biography }}</p>
        </div>
        <create-post
          id="profile-create-post"
          v-if="
            $store.state.account.accountId ===
              $store.state.currentAccount.accountId && showBio === true
          "
        />
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
      showBio: false,
      // scrollDistance: 0,
    };
  },
  created() {
    AccountService.getAccountOther(this.$route.params.id).then((response) => {
      this.$store.commit("SET_ACCOUNT", response.data);
      // console.log(this.$store.state.account);
      this.showBio = true;
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
#bio-create-box {
  display: flex;
  flex-direction: column;
  text-align: center;
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

.profile-bio {
  padding-right: 20px;
  padding-left: 20px;
  padding-bottom: 0px;
  padding-top: 0px;
  max-width: 300px;
  border-radius: 10px;
  /* padding-top: 20px; */
  border-left: 2px #d1d1d3 solid;
  border-right: 2px #d1d1d3 solid;
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