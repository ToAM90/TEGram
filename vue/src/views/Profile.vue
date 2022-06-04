<template>
  <div id="home">
    <profile-header id="profile-header"></profile-header>
    <image-column class="profile-image-column" />
  </div>
</template>

<script>
import ProfileHeader from "@/components/ProfileHeader.vue";
import ImageColumn from "../components/ImageColumn.vue";

export default {
  name: "profile",

  components: {
    ProfileHeader,
    ImageColumn,
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
  /* width: 100vw; */
  position: fixed;
}

.profile-image-column {
  padding-top: 150px;
  margin-left: 20px;

  margin-right: 20px;
}
</style>