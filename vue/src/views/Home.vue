<template>
  <div id="home">
    <SideBar id="side-bar"></SideBar>
  
    <ImageColumn class='image-column'></ImageColumn>
  </div>
</template>

<script>
import SideBar from "@/components/SideBar.vue";
import ImageColumn from "@/components/ImageColumn.vue";

export default {
  name: "home",
  
  components: {
    SideBar,
    ImageColumn
  },
};
</script>

<style>

#home {
  max-width: 100vw
  /* align-items: space-between; */
  /* justify-content: space-between; */
}

#side-bar {
  width: 275px;
  margin: 0px 0px 0px 0px;
  height: 100vh;
  position: fixed;
}


#image-column {
  margin-left: 275px;
  width: 100%
}
</style>