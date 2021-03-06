package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.UUID;

import controller.APIController.RequestType;
import model.Address;

public class AddressDAO extends BaseDAO
{
	public final static String BASE_URL = "address/";

	public AddressDAO()
	{}

	public int insertOrUpdate(Address a)
	{
		Address exists = this.selectOne(a.getAddressID().toString());

		if (exists == null)
			return this.insert(a);
		else
			return this.update(a);
	}
	
	public int insert(Address a)
	{
		PreparedStatement ps = null;

		String sql = "INSERT INTO Address VALUES(?,?,?,?,?,?,?)";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, a.getAddressID().toString());
			ps.setString(2, a.getStreet());
			ps.setInt(3, a.getNumber());
			ps.setString(4, a.getCity());
			ps.setInt(5, a.getZipCode());
			ps.setString(6, a.getCoordinates());
			ps.setLong(7, a.getLastUpdated());

			if (!isSyncFunction)
			{
				params = new HashMap<String, String>();
				params.put("addressID", a.getAddressID().toString());
				params.put("street", a.getStreet());
				params.put("number", Integer.toString(a.getNumber()));
				params.put("city", a.getCity());
				params.put("zipCode", Integer.toString(a.getZipCode()));
				params.put("coordinates", a.getCoordinates());
				params.put("lastUpdated", Long.toString(a.getLastUpdated()));
			}
			
			return ps.executeUpdate();

		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		finally {
			try {
				if (ps != null)
					ps.close();
				if (!isSyncFunction)
					syncMainDB(BASE_URL + "create", RequestType.POST, params);
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}

	}
	
	public int update(Address a)
	{
		PreparedStatement ps = null;

		String sql = "UPDATE Address SET `Street`=?, `Number`=?,`City`=?,`ZipCode`=?,`Coordinates`=?,`LastUpdated`=? WHERE `AddressID`=?";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, a.getStreet());
			ps.setInt(2, a.getNumber());
			ps.setString(3, a.getCity());
			ps.setInt(4, a.getZipCode());
			ps.setString(5, a.getCoordinates());
			ps.setLong(6, a.getLastUpdated());
			ps.setString(7, a.getAddressID().toString());

			if (!isSyncFunction)
			{
				params = new HashMap<String, String>();
				params.put("addressID", a.getAddressID().toString());
				params.put("street", a.getStreet());
				params.put("number", Integer.toString(a.getNumber()));
				params.put("city", a.getCity());
				params.put("zipCode", Integer.toString(a.getZipCode()));
				params.put("coordinates", a.getCoordinates());
				params.put("lastUpdated", Long.toString(a.getLastUpdated()));
			}

			return ps.executeUpdate();

		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		finally {
			try {
				if (ps != null)
					ps.close();
				if (!isSyncFunction)
					syncMainDB(BASE_URL + "update/" + params.get("addressID"), RequestType.PUT, params);
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}

	}

	public ArrayList<Address> selectAll()
	{
		ArrayList<Address> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT a.`AddressID`, a.`Street`, a.`Number`, a.`City`, a.`ZipCode`, a.`Coordinates`, a.`LastUpdated` as AddressLastUpdated FROM Address a;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<Address>();

			while (rs.next()) {
				list.add(resultToModel(rs));
			}

			return list;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}

	}

	public TreeMap<String, String> updateStatus()
	{
		TreeMap<String, String> map = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(DISTINCT AddressID) as Count, MAX(LastUpdated) as LastUpdated FROM Address";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			map = new TreeMap<String, String>();

			while (rs.next()) {
				map.put("Count", rs.getString("Count"));
				map.put("LastUpdated", rs.getString("LastUpdated"));
			}

			return map;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}
	}

	public Address selectOne(String addressID)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT a.`AddressID`, a.`Street`, a.`Number`, a.`City`, a.`ZipCode`, a.`Coordinates`, a.`LastUpdated` as AddressLastUpdated FROM Address a WHERE AddressID=?";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, addressID);
			rs = ps.executeQuery();
			if (rs.next())
				return resultToModel(rs);
			else
				return null;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}
	}

	public static Address resultToModel(ResultSet rs) throws SQLException
	{
		Address a = new Address();
		a.setAddressID(UUID.fromString(rs.getString("AddressID")));
		a.setStreet(rs.getString("Street"));
		a.setNumber(rs.getInt("Number"));
		a.setCity(rs.getString("City"));
		a.setZipCode(rs.getInt("ZipCode"));
		a.setCoordinates(rs.getString("Coordinates"));
		a.setLastUpdated(rs.getLong("AddressLastUpdated"));
		return a;
	}

	public static void createTable(Connection con)
	{
		PreparedStatement ps = null;

		String sql = 
				"CREATE TABLE IF NOT EXISTS `Address` (" 
				+ "`AddressID` varchar(36) NOT NULL DEFAULT '0',"
				+ "`Street` varchar(50) NOT NULL," 
				+ "`Number` int(5) NOT NULL," 
				+ "`City` varchar(30) NOT NULL,"
				+ "`ZipCode` int(4) NOT NULL," 
				+ "`Coordinates` varchar(40) DEFAULT NULL,"
				+ "`LastUpdated` bigint(14) NOT NULL," 
				+ "PRIMARY KEY (`AddressID`)" 
				+ ");";

		try {

			if (con.isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		finally {
			try {
				if (ps != null)
					ps.close();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException("error.unexpected");
			}
		}
	}
}