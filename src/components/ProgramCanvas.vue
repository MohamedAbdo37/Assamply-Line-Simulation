<template>
    <div class="canvas">
        <v-stage id="stage" ref="stage" :config="stageSize" @mousedown="handleStageMouseDown"
            @touchstart="handleStageMouseDown">
            <v-layer>
                <v-group v-for="item in queues" :key="item.id" :config="item">
                    <v-rect :config="item.body" />
                    <v-text :config="item.text" />
                    <v-text :config="item.queue" />
                </v-group>

                <v-group v-for="item in machines" :key="item.id" :config="item">
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
            line: false,
        }
    },
    props: ['machine', 'queue', 'mColor', 'qColor', 'clear', 'relation'],
    watch: {
        machine() {
            this.createM(this.machine);
        }
        , queue() {
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
                this.relations = []
                this.createQ(this.queue);
            }
        },
        async relation() {
            console.log(this.relation);
            console.log("line1", this.line);
            this.line = this.relation;
            console.log("line2", this.line);
            await this.drewArrow(0).then(() => {
                console.log("line3", this.line);
                document.querySelector(".canvas").removeEventListener("click", console.log("End"));
                this.$emit('lineDone', false)
            });


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
        async drewArrow(r) {
            console.log("arrow")
            if (this.line === true) {
                let n = 0;
                let R = this.createR(1);
                document.querySelector(".canvas").addEventListener("click", () => {
                    
                    n = n + 1;
                    console.log(this.localMousePos);
                    if (R) {
                        R.points.push(this.localMousePos.x - R.x);
                        R.points.push(this.localMousePos.y - R.y);
                    }
                    console.log(R.points);
                    console.log(R);
                    if (n === 2) {
                        console.log(n)
                        this.relations.push(R);
                        R = this.createR(r);
                        this.line = false;
                        document.querySelector(".canvas").removeEventListener("click", console.log("End"));
                        return;
                    }
                })
            }
            else return
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
            const localX = event.clientX - 10;
            const localY = event.clientY - 80;
            this.localMousePos = { x: localX, y: localY };
        });
    },
    mounted() {
        this.setStageSize();
        this.createQ(this.queue);
    },
    unmounted() {
        document.querySelector(".canvas").removeEventListener("click", console.log("End"));
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