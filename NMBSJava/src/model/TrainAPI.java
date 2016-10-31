package model;

import org.json.JSONObject;

public class TrainAPI {
	private JSONObject json;
	private int number;
	private int traintype;
	private String fullId;
	private String departureStation;
	private String terminusStation;
	private StopAPI stop;
	private boolean cancelled;
	private TimeAPI time;
	private String Jid;
	private String Cid;

	public TrainAPI(JSONObject json) {
		if (!json.get("Number").equals(null)) {
			this.number = json.getInt("Number");
		} else
			this.number = 0;
		this.traintype = json.getInt("TrainType");
		this.fullId = json.getString("FullId");
		this.departureStation = json.getString("DepartureStation");
		this.terminusStation = json.getString("TerminusStation");
		this.stop = new StopAPI(json.getJSONObject("Stops"));
		this.cancelled = json.getBoolean("Cancelled");
		this.time = new TimeAPI(json.getJSONObject("Time"));
		if (!json.get("Jid").equals(null)) {
			this.Jid = (String)json.get("Jid");
		} else
			this.Jid = "";
		if (!json.get("Cid").equals(null)) {
			this.Cid = (String)json.get("Cid");
		} else
			this.Cid = "";
	}
	
	public TrainAPI(String trein) {
		try {
			json = new JSONObject(RouteberekeningAPI.readUrl("https://traintracks.online/api/Train/" + trein));
			if (!json.get("Number").equals(null)) {
				this.number = json.getInt("Number");
			} else
				this.number = 0;
			this.traintype = json.getInt("TrainType");
			this.fullId = json.getString("FullId");
			this.departureStation = json.getString("DepartureStation");
			this.terminusStation = json.getString("TerminusStation");
			this.stop = new StopAPI(json.getJSONObject("Stops"));
			this.cancelled = json.getBoolean("Cancelled");
			this.time = new TimeAPI(json.getJSONObject("Time"));
			if (!json.get("Jid").equals(null)) {
				this.Jid = (String)json.get("Jid");
			} else
				this.Jid = "";
			if (!json.get("Cid").equals(null)) {
				this.Cid = (String)json.get("Cid");
			} else
				this.Cid = "";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getTraintype() {
		return traintype;
	}

	public void setTraintype(int traintype) {
		this.traintype = traintype;
	}

	public String getFullId() {
		return fullId;
	}

	public void setFullId(String fullId) {
		this.fullId = fullId;
	}

	public String getDepartureStation() {
		return departureStation;
	}

	public void setDepartureStation(String departureStation) {
		this.departureStation = departureStation;
	}

	public String getTerminusStation() {
		return terminusStation;
	}

	public void setTerminusStation(String terminusStation) {
		this.terminusStation = terminusStation;
	}

	public StopAPI getStop() {
		return stop;
	}

	public void setStop(StopAPI stop) {
		this.stop = stop;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public TimeAPI getTime() {
		return time;
	}

	public void setTime(TimeAPI time) {
		this.time = time;
	}

	public String getJid() {
		return Jid;
	}

	public void setJid(String jid) {
		Jid = jid;
	}

	public String getCid() {
		return Cid;
	}

	public void setCid(String cid) {
		Cid = cid;
	}
}
