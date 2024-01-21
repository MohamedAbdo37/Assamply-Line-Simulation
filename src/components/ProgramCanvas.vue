<template>
    <div class="canvas">
        <v-stage id="stage" ref="stage" :config="stageSize" @mousedown="handleStageMouseDown"
            @touchstart="handleStageMouseDown">
            <v-layer>
                <v-group v-for="item in machines" :key="item.id" :config="item" @transformend="handleTransformEnd"
                    @dragend="handleTransformEnd">
                    <v-circle :config="item.body"/>
                    <v-text :config="item.text" />
                </v-group>
                <v-group v-for="item in queues" :key="item.id" :config="item">
                    <v-rect v-for="item in rectangles" :key="item.id" :config="item" @transformend="handleTransformEnd"
                        @dragend="handleTransformEnd" />
                </v-group>
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
            machine: undefined,
            queue: undefined,
            machines: [

            ],
            machineTexts: [
            ],
            machineBodies: [
            ],
            queues: [
                {
                    name: "Q0",
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
    props:['m'],
    watch: {
        machine(){
            this.createM(this.machine);
        }
    },
    methods: {
        handleStageMouseDown() {

        },
        handleTransformEnd() {

        },
        setStageSize() {
            this.stageSize.height = document.querySelector(".canvas").offsetHeight;
            this.stageSize.width = document.querySelector(".canvas").offsetWidth;
        },
        createM(m) {
            let mName = "M" + String(m);
            
            const mText = {
                name: mName,
                text: mName,
                fontSize: 20,
                x: 8,
                y: 42,
                fill: "white"
            }

            const mBody = {
                name: mName,
                radius: 25,
                x: 20,
                y: 50,
                fill: 'green'
            }

            const group = {
                name: mName,
                text: mText,
                body: mBody,
                x: 20,
                y: 50,
                draggable: true
            }
            this.machines.push(group);
        }
    },
    created() {
        window.onresize = () => {
            this.setStageSize();
        }
    },
    mounted() {
        this.setStageSize();
        this.createM(this.machine);
        
    },
}
</script>

<style>
.canvas {
    background-color: rgb(193, 208, 208);
    border-radius: 10px;
    height: 400px;
    margin: 10px;
    z-index: 0;
}

#stage {
    margin: 5px;
}
</style>