public class OneLaneBridge extends Bridge {
    private int bLimitT;
    private Object condition = new Object();
    public OneLaneBridge(int bLimit){
        bLimitT = bLimit;
    }
  
    @Override
    public void arrive(Car car) throws InterruptedException{
        synchronized(condition) {
            if(bridge.isEmpty()){
                direction = car.getDirection();
            }

            try{
                while(bridge.size() == bLimitT || car.getDirection() != direction){
                    condition.wait();
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            car.setEntryTime(currentTime);
            currentTime++;
            bridge.add(car);
            System.out.println("Bridge (dir=" + direction + "):" + bridge);

        }
    }


    @Override
    public void exit(Car car) throws InterruptedException{
        synchronized(condition){
            if(bridge.get(0).equals(car)){
             bridge.remove(car);
             System.out.println("Bridge (dir=" + direction + "):" + bridge);
             if(bridge.size() < bLimitT){
                condition.notifyAll();
             }
            }else{
                while(bridge.get(0).equals(car)){
                    condition.wait();
                }
                bridge.remove(0);
                System.out.println("Bridge (dir =" + direction + "): " + bridge);
                 if(bridge.size() < bLimitT){
                 condition.notifyAll();
              }
            }
            if(bridge.isEmpty()){
            direction = !direction;
            }
        }
    }
}
