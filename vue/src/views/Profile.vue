<template>
  <div id="home">
    <profile-header id="profile-header"></profile-header>
    <create-post></create-post>
    <image-column />
  </div>
</template>

<script>
import ProfileHeader from "@/components/ProfileHeader.vue";
import ImageColumn from "../components/ImageColumn.vue";
import CreatePost from "@/components/CreatePost.vue";

export default {
  name: "profile",

  components: {
    ProfileHeader,
    ImageColumn,
    CreatePost,
  },
  created() {
    // this.$store.commit("SET_ACCOUNT", this.$route.params.id);
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
  width: 100vw;
  position: fixed;
}
</style>