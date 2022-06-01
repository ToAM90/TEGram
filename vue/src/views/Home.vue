<template>
  <div class="home">
    <SideBar></SideBar>
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
  </div>
</template>

<script>
import SideBar from "@/components/SideBar.vue"

export default {
  name: "home",
  components: {
    SideBar
  }
};
</script>

<style>
  .home {
    display: flex;
    flex-direction: row;
  }

</style>