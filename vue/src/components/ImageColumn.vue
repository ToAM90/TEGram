<template>
  <div class="my-images" > 
    <div class='feed-post-container' v-for="image in images" v-bind:key="image.id">
      <img class="image"  v-bind:src="image" alt="" />
      
      <div class='post-interaction-bar'>
        <img class='like-icon interaction-icon' @click='like' v-if='liked' src="@/resources/icons8-heart-50 (outline).png" alt="">
        
        <img class='like-icon interaction-icon' @click='like' v-else src="@/resources/icons8-heart-50 (outline).png" alt="">
        <p>view more</p>
        <img class='favorite-icon interaction-icon' @click='like' src="@/resources/icons8-star-50 (outline).png" alt="">
      </div>
    </div>
  </div>
  
</template>

<script>
export default {
  name: 'image-column',
  computed: {
    images() {
      return this.$store.state.images;
    },
  },
  methods: {
    like() {
      this.image.id
    }
  }
};
</script>

<style>
@media only screen and (max-width: 800px)
  {
    .my-images {
      margin-left: 295px;
      line-height: 0;
      height: 100%;
      
    
      background: var(--primary-background-color);
    }
  }

@media only screen and (max-width: 1400px)
  {
    .my-images {
      margin-left: 295px;
      line-height: 0;
      height: 100%;
      
      -webkit-column-count: 2;
      -webkit-column-gap:   0px;
      -moz-column-count:    2;
      -moz-column-gap:      0px;
      column-count:         2;
      column-gap:           0px;  
      background: var(--primary-background-color);
    }
  }
  @media only screen and (min-width: 1400px)
  {
  .my-images {
    margin-left: 295px;
    line-height: 0;
    height: 100%;
    
    -webkit-column-count: 3;
    -webkit-column-gap:   0px;
    -moz-column-count:    3;
    -moz-column-gap:      0px;
    column-count:         3;
    column-gap:           0px;  
    background: var(--primary-background-color);
  }
  }

  

  

  .my-images .feed-post-container {
    margin-left: 0px;
    margin-right: 0px;
    width: calc(100% - 40px)!important;
    height: auto !important;
    box-shadow: 0px 0px 5px rgb(172, 169, 169);
    border-radius: 5%;
    
    /* margin-top: 5px; */
    border-top: 0px
  }

  .my-images .feed-post-container > .image{
    width: calc(100%)!important;
  }


  .post-interaction-bar {
    background: white;

    box-shadow: 0px 0px 5px rgb(172, 169, 169);
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    height: 20px;
    margin-bottom: 5px;
    color: black;
  }

  .interaction-icon {
    max-height: auto;
    max-width: 20px
  }

  .like-icon {
  }
 
  .image-info {
    border-left: solid black 3px;
    /* max-width: 20%; */
    color: black;
    padding-left: 5px
  }
</style>