package bean;

public class szzf {
	  private String Area;
      private String Region;
      private String Community;
      private int Room;
      private int Hall;
      private int Meter;
      private int Price;
      private int PricePRoom;
      private int PricePMeter;

      public szzf() {
          this.Area = null;
          this.Region = null;
          this.Community = null;
          this.Room = 0;
          this.Hall = 0;
          this.Meter = 0;
          this.Price = 0;
          this.PricePRoom = 0;
          this.PricePMeter = 0;
      }
      
      public szzf(String Area, String Region, String Community, int Room, int Hall, int Meter, int Price, int PricePRoom, int PricePMeter) {
          this.Area = Area;
          this.Region = Region;
          this.Community = Community;
          this.Room = Room;
          this.Hall = Hall;
          this.Meter = Meter;
          this.Price = Price;
          this.PricePRoom = PricePRoom;
          this.PricePMeter = PricePMeter;
      }

      public String getArea() {
          return Area;
      }

      public void setArea(String Area) {
          this.Area = Area;
      }

      public String getRegion() {
          return Region;
      }

      public void setRegion(String Region) {
          this.Region = Region;
      }

      public String getCommunity() {
          return Community;
      }

      public void setCommunity(String Community) {
          this.Community = Community;
      }

      public int getRoom() {
          return Room;
      }

      public void setRoom(int Room) {
          this.Room = Room;
      }
      
      public int getHall() {
          return Hall;
      }

      public void setHall(int Hall) {
          this.Hall = Hall;
      }
      
      public int getMeter() {
          return Meter;
      }

      public void setMeter(int Meter) {
          this.Meter = Meter;
      }
      
      public int getPricePRoom() {
          return PricePRoom;
      }

      public void setPricePRoom(int PricePRoom) {
          this.PricePRoom = PricePRoom;
      }
      
      public int getPricePMeter() {
          return PricePMeter;
      }

      public void setPricePMeter(int PricePMeter) {
          this.PricePMeter = PricePMeter;
      }
      
      public int getPrice() {
          return Price;
      }

      public void setPrice(int Price) {
          this.Price = Price;
      }
}
