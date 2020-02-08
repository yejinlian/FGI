/********************************************
 * 设备绑定命令的数据结构
 *
 * @author zwq
 * @create 2018-06-03 21:32
 *********************************************/

package deepthinking.fgi.model.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("发给设备的工单结构")
public class TicketListModel {
	@ApiModelProperty("工单编号")
    private String ticketID;        // 工单编号
	@ApiModelProperty("工单实施日期(出现场日期)")
    private String ticketDate;      // 工单实施日期(出现场日期)
	@ApiModelProperty("线路编号")
    private String lineID;          // 线路编号
	@ApiModelProperty("方向标识")
    private String lineDirection;   // 上下行
	@ApiModelProperty("工作区段编号(里程点)")
    private String mileNum;         // 工作区段编号(里程点)
	@ApiModelProperty("受控仪表类型")
    private String instrumentName;  // 受控仪表类型
	@ApiModelProperty("受控仪表编号")
    private String instrumentID;    // 受控仪表编号

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(String ticketDate) {
        this.ticketDate = ticketDate;
    }

    public String getLineID() {
        return lineID;
    }

    public void setLineID(String lineID) {
        this.lineID = lineID;
    }

    public String getLineDirection() {
        return lineDirection;
    }

    public void setLineDirection(String lineDirection) {
        this.lineDirection = lineDirection;
    }

    public String getMileNum() {
        return mileNum;
    }

    public void setMileNum(String mileNum) {
        this.mileNum = mileNum;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public String getInstrumentID() {
        return instrumentID;
    }

    public void setInstrumentID(String instrumentID) {
        this.instrumentID = instrumentID;
    }
}
