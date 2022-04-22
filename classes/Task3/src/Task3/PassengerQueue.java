package Task3;

public class PassengerQueue {
    int front;
    int rear;
    int maxSize;
    String[] passengerString = new String[3];
    String[] queue = new String[6];

    PassengerQueue(){
        maxSize = 6;
        front = -1;
        rear = -1;
    }

    boolean isFull() {
        if (front == 0 && rear == maxSize - 1) {
            return true;
        }
        return false;
    }

    // check if the queue is empty
    boolean isEmpty() {
        if (front == -1)
            return true;
        else
            return false;
    }

    public void delete(){
        if(front==-1 && rear==-1){
            System.out.println("Empty Queue");
        }else if(front==rear){
            front=rear=-1;
        }else{
            front=(front+1)%maxSize;
        }
    }

    public String peekFront(){
        String i = queue[front];
        return i;
    }

    public void insert(String element) {

        if(front == -1 && rear == -1){
            front=rear =0;
            queue[rear] = element;
        }else if(((rear+1)%maxSize)==front){
            System.out.println("The Queue is Full !");
        }else{
            rear = (rear+1)%maxSize;
            queue[rear] = element;
        }
    }

    public void display(){

        int i = front;

        if(front ==-1 && rear==-1){
            System.out.println("The queue is empty !");
        }else{
            while(i!=rear){
                System.out.println(queue[i]);
                i = (i+1)%maxSize;
            }
        }
    }

    public void setPassengerString(int i,String output){
        passengerString[i] = output;
    }
}
