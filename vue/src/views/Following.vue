<template>
  <div>
    <side-bar id="side-bar-home"/>
    <follow-list />
  </div>
</template>

<script>
import FollowList from "../components/FollowList.vue";
import SideBar from "../components/SideBar.vue";
export default {
  components: {
    FollowList,
    SideBar,
  },
};
</script>

<style>
</style>