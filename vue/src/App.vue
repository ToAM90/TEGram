<template>
  <div id="app">
    <router-view id="view" />
  </div>
</template>

<style>
:root {
  --top-color: #38413e;
  --primary-background-color: #c8cfd6;
  --panel-background-color: #f1fffa;
  --sign-up-blue: #5096b9;
  --sign-in-salmon: #fe9aa4;
}

@import url("https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap");

#view {
  color: var(--panel-background-color);
  text-decoration: none;
  font-family: "Open Sans", sans-serif;
  max-height: 100vh;
  max-width: 100vw;
}
#app {
  /* background-color: var(--primary-background-color); */

  /* height: 100vh; */
  min-height: 100vh;
  overflow: auto;
  width: 100vw;

  background-image: url("http://res.cloudinary.com/dcipg5scy/image/upload/v1654357142/TE_GRAM/zelphyniwd4mjons4g3o.png");
  background-repeat: repeat repeat;
  background-size: 100%;

  margin: -8px;
  padding: 0px;
}
</style>
