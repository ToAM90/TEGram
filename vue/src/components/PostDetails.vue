<template>
  <div>
    <div class="post-detail-bar">
      <div class="caption">{{ $store.state.currentPost.caption }}</div>
      <comment></comment>
    </div>
  </div>
</template>

<script>
import Comment from "@/components/Comment.vue";
export default {
  name: "post-details",
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
  /* background-image: url("https://res.cloudinary.com/dcipg5scy/image/upload/v1654569968/TE_GRAM/nchboavjxefno1onx7u7.png");
  -webkit-text-fill-color: transparent;
  -webkit-background-clip: text; */

  color: rgb(35, 33, 37);
  padding: 8px;
  font-size: 1.2rem;

  font-family: "Montserrat", sans-serif;
  font-weight: 800;
  /* white-space: nowrap; */

  word-break: break-all;

  word-break: break-word;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  line-height: 16px;
  max-height: 96px;
  -webkit-line-clamp: 6;
  -webkit-box-orient: vertical;
}

.post-detail-bar {
  margin-top: -4px;
  /* background-image: url("https://res.cloudinary.com/dcipg5scy/image/upload/v1654570644/TE_GRAM/u7k1krgvbdjhnfujrwhy.png"); */
  /* background: #e6e6e9; */
  background: #d1d1d3;
  border-radius: 0px 0px 4px 4px;
}
</style>