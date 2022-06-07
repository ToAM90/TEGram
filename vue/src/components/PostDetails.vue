<template>
  <div>
    <div class="post-detail-bar">
      <textarea
        class="caption-text-area caption"
        v-model="$store.state.currentPost.caption"
      ></textarea>
      <!-- <p class="caption">{{ $store.state.currentPost.caption }}</p> -->
      <comment></comment>
    </div>
  </div>
</template>

<script>
import Comment from "@/components/Comment.vue";
export default {
  name: "post-interaction",
  components: {
    Comment,
  },
  props: {
    post: {
      postId: 0,
      accountId: 0,
      img: "",
      caption: "",
      postDate: "",
      privated: false,
      comments: [],
      liked: false,
      favorited: false,
      likesCount: 0,
    },
    data() {
      return {
        moreDetails: false,
      };
    },
  },
  methods: {
    toggleMoreDetails() {
      this.moreDetails = !this.moreDetails;
    },
  },
  calculated: {
    captionLength() {
      return this.$store.state.caption.length();
    },
  },
};
</script>

<style lang="css" scoped>
.caption {
  background-image: url("http://res.cloudinary.com/dcipg5scy/image/upload/v1654357142/TE_GRAM/zelphyniwd4mjons4g3o.png");

  -webkit-text-fill-color: transparent;
  -webkit-background-clip: text;
  padding: 16px;
  font-size: 1.2rem;

  font-family: "Montserrat", sans-serif;
  font-weight: 800;
}

.caption-text-area {
  margin: 5px;
  margin-bottom: 0px;
  min-width: calc(100% - 42px);

  min-height: calc(captionLength/20 * 26px);
  border: 0px;
  padding-bottom: 16px;
}

.post-detail-bar {
  background: var(--panel-background-color);
  /* box-shadow: 0px 0px 5px rgb(172, 169, 169); */

  padding-bottom: auto;

  margin-top: -22px;

  margin-bottom: 10px;

  /* border-bottom: 5px red solid; */
  border-radius: 0px 0px 4px 4px;
}
</style>