<template>
  <ProgramHeader></ProgramHeader>
  <ProgramCanvas></ProgramCanvas>
  <ProgramControl></ProgramControl>
  <ProgramInformation></ProgramInformation>
</template>

<script>
import ProgramHeader from './components/ProgramHeader.vue'
import ProgramCanvas from './components/ProgramCanvas.vue'
import ProgramControl from './components/ProgramCanvas.vue'
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
#app {
  font-family: Avenir;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  margin-top: 0px;
  color: white;
}
body{
  background-image: url("./assets/background.jpg");

}
</style>
