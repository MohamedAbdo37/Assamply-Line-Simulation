<template>
  <div>
    <ProgramHeader></ProgramHeader>
    <ProgramCanvas></ProgramCanvas>
    <ProgramControl></ProgramControl>
    <ProgramInformation></ProgramInformation>
  </div>
</template>

<script>
import ProgramHeader from './components/ProgramHeader.vue'
import ProgramCanvas from './components/ProgramCanvas.vue'
import ProgramControl from './components/ProgramControl.vue'
import ProgramInformation from './components/ProgramInformation.vue'

export default {
  name: 'App',
  components: {
    ProgramHeader,
    ProgramCanvas,
    ProgramControl,
    ProgramInformation,
  }
}
</script>

<style>
::-webkit-scrollbar {
  width: 5px;
  height: 0px;
}

/* Track */
::-webkit-scrollbar-track {
  background: #170404;
  border-radius: 2.5px;
}

/* Handle */
::-webkit-scrollbar-thumb {
  background: rgb(205, 228, 255);

}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  background: rgb(77, 143, 241);

}

#app {
  font-family: Avenir;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  margin-top: 0px;
  color: white;
}

body {
  background-image: url("./assets/background.jpg");

}
</style>
