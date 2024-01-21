<template>
    <div class="canvas">
        <v-stage id="stage" ref="stage" :config="stageSize" @mousedown="handleStageMouseDown"
            @touchstart="handleStageMouseDown" >
            <v-layer>
                <v-group v-for="item in queues" :key="item.id" :config="item" @transformend="handleTransformEnd"
                    @dragend="handleTransformEnd" >
                    <v-rect :config="item.body" />
                    <v-text :config="item.text" />
                    <v-text :config="item.queue" />
                </v-group>

                <v-group v-for="item in machines" :key="item.id" :config="item" @transformend="handleTransformEnd"
                    @dragend="handleTransformEnd">
                    <v-circle :config="item.body"/>
                    <v-text :config="item.text" />
                </v-group>

                <v-arrow v-for="item in relations" :key="item.id" :config="item" @transformend="handleTransformEnd"
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
            machines: [],
            queues: [],
            relations: [],
            localMousePos: { x: undefined, y: undefined },
        }
    },
    props:['machine', 'queue', 'mColor', 'qColor', 'clear'],
    watch: {
        machine() {
            this.createM(this.machine);
        }
        , queue() {
            this.createQ(this.queue);
        },
        mColor(){
            for (let i = 0; i < this.machines.length; i++){
                this.machines[i].body.fill = this.mColor
            }
        },
        qColor(){
            for (let i = 0; i < this.queues.length; i++){
                this.queues[i].body.fill = this.qColor
            }
        },
        clear(){
            if (this.clear){
                this.queues = []
                this.machines = []
                this.createQ(this.queue);
            }
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
        createR(r) {
            const relation = {
                name: r,
                x: this.localMousePos.x,
                y: this.localMousePos.y,
                from:undefined,
                to: undefined,
                points: [0, 0, 0 , 0],
                pointerLength: 10,
                pointerWidth: 10,
                fill: 'black',
                stroke: 'black',
                strokeWidth: 2,
            }
            this.relations.push(relation);
            return relation;
        },
        flash(name) {
            this.machines.forEach(m => {
                if (m.name === name) {
                    m.body.fill = 'white';
                    setTimeout(() => {
                        m.body.fill = this.mColor;
                    }, 200);

                }
            })
        },
        changeMC(name, color){
            for (let i=0; i < this.machines.length; i++){
                if (this.machines[i].name == name){
                    this.machines[i].body.fill = color
                }
            }
        },
        changeQC(name, color){
            for (let i=0; i < this.machines.length; i++){
                if (this.queues[i].name == name){
                    this.queues[i].body.fill = color
                }
            }
        },
        inMachine(name){
            for (let i=0; i < this.machines.length; i++){
                if (this.machines[i].name == name){
                    let temp = Number(this.machines[i].queue.text)
                    ++temp
                    this.machines[i].queue.text = temp
                }
            }
        },
        deMachine(name){
            for (let i=0; i < this.machines.length; i++){
                if (this.machines[i].name == name){
                    let temp = Number(this.machines[i].queue.text)
                    --temp
                    this.machines[i].queue.text = temp
                }
            }
        },
        inQueue(name){
            for (let i=0; i < this.queues.length; i++){
                if (this.queues[i].name == name){
                    let temp = Number(this.queues[i].queue.text)
                    ++temp
                    
                    this.queues[i].queue.text = temp
                }
            }
        },
        deQueue(name){
            for (let i=0; i < this.queues.length; i++){
                if (this.queues[i].name == name){
                    let temp = Number(this.queues[i].queue.text)
                    --temp
                    this.queues[i].queue.text = temp
                }
            }
        }
    },
    created() {
        window.onresize = () => {
            this.setStageSize();
        }

        window.addEventListener('mousemove', (event) => {
            const localX = event.clientX - event.target.offsetLeft;
            const localY = event.clientY - event.target.offsetTop;
            this.localMousePos = { x: localX, y: localY };
        });
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