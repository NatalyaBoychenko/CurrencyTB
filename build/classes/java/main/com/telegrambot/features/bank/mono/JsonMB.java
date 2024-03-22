
package com.telegrambot.features.bank;
import com.telegrambot.features.currency.dto.Currency;

public class JsonMB {
    private Currency ccy;
    private Currency base_ccy;
    private float buy;
    private float sale;
//edit
    public JsonMB() {
    }

    public Currency getCcy() {
        return this.ccy;
    }

    public Currency getBase_ccy() {
        return this.base_ccy;
    }

    public float getBuy() {
        return this.buy;
    }

    public float getSale() {
        return this.sale;
    }

    public void setCcy(Currency ccy) {
        this.ccy = ccy;
    }

    public void setBase_ccy(Currency base_ccy) {
        this.base_ccy = base_ccy;
    }

    public void setBuy(float buy) {
        this.buy = buy;
    }

    public void setSale(float sale) {
        this.sale = sale;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof JsonMB)) {
            return false;
        } else {
            JsonMB other = (JsonMB)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (Float.compare(this.getBuy(), other.getBuy()) != 0) {
                return false;
            } else if (Float.compare(this.getSale(), other.getSale()) != 0) {
                return false;
            } else {
                label40: {
                    Object this$ccy = this.getCcy();
                    Object other$ccy = other.getCcy();
                    if (this$ccy == null) {
                        if (other$ccy == null) {
                            break label40;
                        }
                    } else if (this$ccy.equals(other$ccy)) {
                        break label40;
                    }

                    return false;
                }

                Object this$base_ccy = this.getBase_ccy();
                Object other$base_ccy = other.getBase_ccy();
                if (this$base_ccy == null) {
                    if (other$base_ccy != null) {
                        return false;
                    }
                } else if (!this$base_ccy.equals(other$base_ccy)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof JsonMB;
    }

    public int hashCode() {
        int PRIME = true;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.getBuy());
        result = result * 59 + Float.floatToIntBits(this.getSale());
        Object $ccy = this.getCcy();
        result = result * 59 + ($ccy == null ? 43 : $ccy.hashCode());
        Object $base_ccy = this.getBase_ccy();
        result = result * 59 + ($base_ccy == null ? 43 : $base_ccy.hashCode());
        return result;
    }

    public String toString() {
        Currency var10000 = this.getCcy();
        return "JsonPB(ccy=" + var10000 + ", base_ccy=" + this.getBase_ccy() + ", buy=" + this.getBuy() + ", sale=" + this.getSale() + ")";
    }
}
