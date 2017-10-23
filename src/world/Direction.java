package world;

public class Direction {
    int value;

    public Direction(int value) {
        this.value = value;
    }

    public Direction(char c) {
        switch (c) {
            case 'w':
                this.value = 1;
                break;
            case 'a':
                this.value = 2;
                break;
            case 's':
                this.value = 3;
                break;
            case 'd':
                this.value = 0;
                break;
            default:
                break;
        }
    }

    public int getXShift() {
        switch(value) {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return -1;
            case 3:
                return 0;
            default:
                return 0;
        }
    }

    public int getYShift() {
        switch(value) {
            case 0:
                return 0;
            case 1:
                return -1;
            case 2:
                return 0;
            case 3:
                return 1;
            default:
                return 0;
        }
    }

    public String toString() {
        switch(value) {
            case 0:
                return "East";
            case 1:
                return "North";
            case 2:
                return "West";
            case 3:
                return "South";
            default:
                return "DirectionError";
        }
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Direction) {
            if(((Direction) o).value == value) {
                return true;
            }
        }

        return false;
    }
}
