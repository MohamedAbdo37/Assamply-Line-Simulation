<template>
    <div class="canvas">
        <v-stage id="stage" ref="stage" :config="stageSize" @mousedown="handleStageMouseDown" @touchstart="handleStageMouseDown">
            <v-layer>
                <v-rect v-for="item in rectangles" :key="item.id" :config="item" @transformend="handleTransformEnd"
                @dragend="handleTransformEnd" />
                <v-circle v-for="item in circles" :key="item.id" :config="item" @transformend="handleTransformEnd"
                @dragend="handleTransformEnd" />
            </v-layer>
        </v-stage>
    </div>
    
</template>

<script>
export default {
    name: 'ProgramCanvas',
    data() {
        return {
            stageSize: {
                width: 100,
                height: 400,
            },
            circles: [],
            rectangles: [
                {
                    x: 20,
                    y: 50,
                    width: 100,
                    height: 100,
                    fill: 'red',
                    shadowBlur: 10,
                    draggable: true
                }
            ],
        }
    },
    methods: {
        handleStageMouseDown() {

        },
        handleTransformEnd() {

        },
        setStageSize(){
            this.stageSize.height = document.querySelector(".canvas").offsetHeight;
            this.stageSize.width = document.querySelector(".canvas").offsetWidth;
        }
    },
    created() {
        window.onresize = () => {
            this.setStageSize();
        }
    },
    mounted() {
        this.setStageSize();
    },
}
</script>

<style>
.canvas {
    background-color: rgb(193, 208, 208);
    border-radius: 10px;
    height: 400px;
    margin: 10px;
    z-index: -1;
}

#stage{
    margin: 5px;
}
</style>