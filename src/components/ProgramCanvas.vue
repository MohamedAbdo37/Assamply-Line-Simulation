<template>
    <div class="canvas">
        <v-stage id="stage" ref="stage" :config="stageSize" @mousedown="handleStageMouseDown"
            @touchstart="handleStageMouseDown">
            <v-layer>
                <v-group v-for="item in queues" :key="item.id" :config="item" @transformend="handleTransformEnd"
                    @dragend="handleTransformEnd">
                    <v-rect :config="item.body" />
                    <v-text :config="item.text" />
                    <v-text :config="item.queue" />
                </v-group>
                
                <v-group v-for="item in machines" :key="item.id" :config="item" @transformend="handleTransformEnd"
                    @dragend="handleTransformEnd">
                    <v-circle :config="item.body" />
                    <v-text :config="item.text" />
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
            // machine: -1,
            // queue: -1,
            machines: [],
            queues: [],
        }
    },
    props:['machine', 'queue', 'mColor', 'qColor'],
    watch: {
        machine() {
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
                fill: this.mColor
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
        },
        createQ(q) {
            let qName = "Q" + String(q);

            const qText = {
                name: qName,
                text: qName,
                fontSize: 19,
                x: 55,
                y: 56,
                fill: "white"
            }

            const qQueue = {
                name: qName,
                text: 0,
                fontSize: 20,
                x: 60,
                y: 78,
                fill: "white",
            }

            const qBody = {
                name: qName,
                x: 20,
                y: 50,
                width: 100,
                height: 50,
                fill: this.qColor,
                shadowBlur: 10
            }


            const group = {
                name: qName,
                text: qText,
                body: qBody,
                queue: qQueue,
                x: 20,
                y: 50,
                draggable: true
            }
            this.queues.push(group);
        },
        flash(name){
            this.machines.forEach(m =>{
                if( m.name === name){
                    m.body.fill = 'white';
                    setTimeout(() => {
                        m.body.fill = this.mColor;
                    }, 200);
                    
                }
            })
        }
    },
    created() {
        window.onresize = () => {
            this.setStageSize();
        }
    },
    mounted() {
        this.setStageSize();
        this.createQ(this.queue);
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