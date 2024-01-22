<template>
  <div>
    <ProgramHeader @mColor="this.mColor = $event" @qColor="this.qColor = $event"></ProgramHeader>
    <ProgramCanvas :machine=Ms :queue=Qs :mColor=mColor :qColor=qColor :clear=clear :relation=createR
      @lineDone="this.createR = $event" :mStatues=mList :qStatues=qList ></ProgramCanvas>
    <ProgramControl :m=Ms :q=Qs @mChange="this.Ms = $event" @qChange="this.Qs = $event" @clear="this.clear = $event"
      @addR="this.createR = $event" @machinesList="this.mList = $event" @queuesList="this.qList = $event">
    </ProgramControl>
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
  },
  data() {
    return {
      Ms: -1,
      Qs: 0,
      mColor: "#089DDD",
      qColor: "#ddaf08",
      clear: false,
      createR: false,
      mList: [],
      qList: [],
    }
  },
  mounted() {
  },
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