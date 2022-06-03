<template>
  <div id="home">
    <SideBar id="side-bar"></SideBar>
  </div>
</template>

<script>
import SideBar from "@/components/SideBar.vue";

export default {
  name: "home",

  components: {
    SideBar,
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