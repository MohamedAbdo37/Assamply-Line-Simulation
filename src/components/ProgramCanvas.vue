<template>
    <div class="canvas">
        <!-- <v-stage ref="stage" :config="stageSize" @mousedown="handleStageMouseDown" @touchstart="handleStageMouseDown">
            <v-rect v-for="item in rectangles" :key="item.id" :config="item" @transformend="handleTransformEnd"
                @dragend="handleTransformEnd" />
            <v-circle v-for="item in circles" :key="item.id" :config="item" @transformend="handleTransformEnd"
                @dragend="handleTransformEnd" />
        </v-stage> -->
    </div>
    <ProgramControl></ProgramControl>
</template>

<script>
import ProgramControl from './ProgramControl.vue'

// const width = document.querySelector(".canvas").width;
// const height = document.querySelector(".canvas").height;
export default {
    name: 'ProgramCanvas',
    components: {ProgramControl},
    data() {
        return {
            // stageSize: {
            //     width: width,
            //     height: height,
            // },
            circles: [],
            rectangles: [],
        }
    },
    methods: {
        handleStageMouseDown() {

        },
        handleTransformEnd() {

        }
    }
}
</script>

<style>
.canvas {
    background-color: rgb(193, 208, 208);
    border-radius: 20px;
    height: 400px;
    margin: 10px;
    z-index: -1;
}
</style>