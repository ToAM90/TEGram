<template>
  <div id="home">
    <SideBar id="side-bar"></SideBar>
    <image-column />
  </div>
</template>

<script>
import SideBar from "@/components/SideBar.vue";
import ImageColumn from '../components/ImageColumn.vue';

export default {
  name: "home",

  components: {
    SideBar,
    ImageColumn,
  },
  created(){
    this.$store.state.accountId = this.$route.params.id;
    
  }
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