export class Node {
    name: string;
    data: number;
}
export class BackpropagationNetwork {
    error: number;
    outputNodes: Node[];
    hiddenNodes: Node[];
    weightMap: Map<string, number>;
    inputNodes: Node[];
}
