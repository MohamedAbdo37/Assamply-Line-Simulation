<template>
    <div class="canvas">
        <v-stage id="stage" ref="stage" :config="stageSize" @mousedown="handleStageMouseDown"
            @touchstart="handleStageMouseDown">
            <v-layer>
                <v-group v-for="item in queues" :key="item.id" :config="item" >
                    <v-rect :config="item.body" />
                    <v-text :config="item.text" />
                    <v-text :config="item.queue" />
                </v-group>

                <v-group v-for="item in machines" :key="item.id" :config="item" >
                    <v-circle :config="item.body" />
                    <v-text :config="item.text" />
                </v-group>

                <v-arrow v-for="item in relations" :key="item.id" :config="item" />
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
            selectedShapeID: "",
            n: 0
        }
    },
    props: ['machine', 'queue', 'mColor', 'qColor', 'clear', 'relation'],
    watch: {
        machine() {
            this.createM(this.machine);
        }
        ,queue() {
            this.createQ(this.queue);
        },
        mColor() {
            for (let i = 0; i < this.machines.length; i++) {
                this.machines[i].body.fill = this.mColor
            }
        },
        qColor() {
            for (let i = 0; i < this.queues.length; i++) {
                this.queues[i].body.fill = this.qColor
            }
        },
        clear() {
            if (this.clear) {
                this.queues = []
                this.machines = []
                this.createQ(this.queue);
            }
        },
        relation() {
            if (this.relation == true){
                console.log("jjhkjh" + this.relation)
                var R = this.createR(0);

                document.querySelector(".canvas").addEventListener("click", () => {
                    
                    ++this.n;
                    console.log(this.localMousePos);
                    R.points.push(this.localMousePos.x - R.x);
                    R.points.push(this.localMousePos.y - R.y);
                    console.log(R.points);
                    console.log(R);
                    if(this.n == 2){
                        this.$emit('lineDone', false)
                        this.n=0
                        console.log("first line in if ")
                        this.relations.push(R)
                        this.d()
                    }
                    console.log("last line in add listener")
                })
            }

            

            // document.querySelector(".canvas").addEventListener("dblclick", () => {
            //     let R = undefined;
            //     if (n !== 0) R = this.relations.pop();
            //     else {
            //         document.querySelector(".canvas").removeEventListener("click", console.log("no target"));
            //         document.querySelector(".canvas").removeEventListener("dblclick", console.log("End"));
            //     }
            //     n++;
            //     console.log(this.localMousePos);
            //     console.log(R.points);
            //     console.log(R);
            //     this.relations.push(R);
            //     document.querySelector(".canvas").removeEventListener("click", console.log("target"));
            //     document.querySelector(".canvas").removeEventListener("dblclick", console.log("End"));
            // })

        }
    },
    methods: {
        d(){
            document.querySelector(".canvas").removeEventListener("click", console.log("End"));
        },
        handleStageMouseDown(e) {
            // clicked on transformer - do nothing
            if (e.target.getParent() === null) {
                this.selectedShapeID = '';
                // this.updateTransformer();
                return;
            }
            const clickedOnTransformer =
                e.target.getParent().className === 'Transformer';
            if (clickedOnTransformer) {
                return;
            }

            // find clicked rect by its name
            const name = e.target.name();
            const m = this.machines.find((r) => r.name === name);
            const q = this.queues.find((r) => r.name === name);

            if (m) {
                this.selectedShapeID = name;
            } else if (q) {
                this.selectedShapeID = name;
            }
            console.log(this.selectedShapeID)
            // this.updateTransformer();
        },
        handleTransformEnd(e) {
            // shape is transformed, let us save new attrs back to the node
            // find element in our state
            const m = this.machines.find(
                (r) => r.name === this.selectedShapeID
            );
            const q = this.queus.find(
                (r) => r.name === this.selectedShapeID
            );

            var shape;

            if (m)
                shape = m
            else if (q)
                shape = q

            console.log(`from transformer ${shape}`)
            // update the state
            shape.x = e.target.x();
            shape.y = e.target.y();
        },
        // updateTransformer() {
        //     // here we need to manually attach or detach Transformer node
        //     const transformerNode = this.$refs.transformer.getNode();
        //     const stage = transformerNode.getStage();
        //     const { selectedShapeID } = this;

        //     const selectedNode = stage.findOne('.' + selectedShapeID);
        //     // do nothing if selected node is already attached
        //     if (selectedNode === transformerNode.node()) {
        //         return;
        //     }

        //     if (selectedNode) {
        //         // attach to another node
        //         transformerNode.nodes([selectedNode]);
        //     } else {
        //         // remove transformer
        //         transformerNode.nodes([]);
        //     }
        // },
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
                from: undefined,
                to: undefined,
                points: [],
                fill: 'black',
                stroke: 'black',
                strokeWidth: 2,
            }
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
        changeMC(name, color) {
            for (let i = 0; i < this.machines.length; i++) {
                if (this.machines[i].name == name) {
                    this.machines[i].body.fill = color
                }
            }
        },
        // changeQC(name, color){
        //     for (let i=0; i < this.machines.length; i++){
        //         if (this.queues[i].name == name){
        //             this.queues[i].body.fill = color
        //         }
        //     }
        // },
        // inMachine(name){
        //     for (let i=0; i < this.machines.length; i++){
        //         if (this.machines[i].name == name){
        //             let temp = Number(this.machines[i].queue.text)
        //             ++temp
        //             this.machines[i].queue.text = temp
        //         }
        //     }
        // },
        // deMachine(name){
        //     for (let i=0; i < this.machines.length; i++){
        //         if (this.machines[i].name == name){
        //             let temp = Number(this.machines[i].queue.text)
        //             --temp
        //             this.machines[i].queue.text = temp
        //         }
        //     }
        // },
        inQueue(name) {
            for (let i = 0; i < this.queues.length; i++) {
                if (this.queues[i].name == name) {
                    let temp = Number(this.queues[i].queue.text)
                    ++temp

                    this.queues[i].queue.text = temp
                }
            }
        },
        deQueue(name) {
            for (let i = 0; i < this.queues.length; i++) {
                if (this.queues[i].name == name) {
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
        // const canvas = document.querySelector(".canvas")
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