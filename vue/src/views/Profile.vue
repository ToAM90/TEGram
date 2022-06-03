<template>
  <div id="home">
    <ProfileHeader id="profile-header"></ProfileHeader>
  </div>
</template>

<script>
import ProfileHeader from "@/components/ProfileHeader.vue";

export default {
  name: "profile",

  components: {
    ProfileHeader,
  },
};
</script>

<style>
#home {
  max-width: 100vw;
}

#side-bar {
  width: 275px;
  margin: 0px 0px 0px 0px;
  height: 100vh;
  position: fixed;
}
</style>