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
import model.TypePass;

public class TypePassDAO extends BaseDAO
{
	public final static String BASE_URL = "typePass/";

	public TypePassDAO()
	{}
	
	public int insertOrUpdate(TypePass tt)
	{
		TypePass exists = this.selectOne(tt.getTypePassID().toString());

		if (exists == null)
			return this.insert(tt);
		else
			return this.update(tt);
	}

	public int insert(TypePass t)
	{
		PreparedStatement ps = null;

		String sql = "INSERT INTO TypePass VALUES(?,?,?,?)";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, t.getTypePassID().toString());
			ps.setString(2, t.getName());
			ps.setDouble(3, t.getPrice());
			ps.setLong(4, t.getLastUpdated());

			if (!isSyncFunction)
			{
				params = new HashMap<String, String>();
				params.put("typePassID", t.getTypePassID().toString());
				params.put("name", t.getName());
				params.put("price", Double.toString(t.getPrice()));
				params.put("lastUpdated", Long.toString(t.getLastUpdated()));
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
	
	public int update(TypePass t)
	{
		PreparedStatement ps = null;

		String sql = "UPDATE TypePass SET `Name`=?, `Price`=?, `LastUpdated`=? WHERE TypePassID=?";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, t.getName());
			ps.setDouble(2, t.getPrice());
			ps.setLong(3, t.getLastUpdated());
			ps.setString(4, t.getTypePassID().toString());

			if (!isSyncFunction)
			{
				params = new HashMap<String, String>();
				params.put("typePassID", t.getTypePassID().toString());
				params.put("name", t.getName());
				params.put("price", Double.toString(t.getPrice()));
				params.put("lastUpdated", Long.toString(t.getLastUpdated()));
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
					syncMainDB(BASE_URL + "update/" + params.get("typePassID"), RequestType.PUT, params);
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

		String sql = "SELECT COUNT(DISTINCT TypePassID) as Count, MAX(LastUpdated) as LastUpdated FROM TypePass";

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

	public ArrayList<TypePass> selectAllSync()
	{
		ArrayList<TypePass> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT t.TypePassID, t.Name, t.Price, t.LastUpdated as TypePassLastUpdated FROM TypePass t;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<TypePass>();

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

	public ArrayList<TypePass> selectAll()
	{
		ArrayList<TypePass> list = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT t.TypePassID, t.Name, t.Price, t.LastUpdated as TypePassLastUpdated FROM TypePass t ORDER BY t.Name;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			list = new ArrayList<TypePass>();

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

	public TypePass selectOne(String typePassID)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT t.TypePassID, t.Name, t.Price, t.LastUpdated as TypePassLastUpdated FROM TypePass t WHERE TypePassID = ?;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, typePassID);
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
	
	public TypePass selectOneOnName(String typePassName)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT t.TypePassID, t.Name, t.Price, t.LastUpdated as TypePassLastUpdated FROM TypePass t WHERE Name = ?;";

		try {

			if (getConnection().isClosed()) {
				throw new IllegalStateException("error unexpected");
			}
			ps = getConnection().prepareStatement(sql);

			ps.setString(1, typePassName);
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

	private TypePass resultToModel(ResultSet rs) throws SQLException
	{
		TypePass t = new TypePass();

		t.setTypePassID(UUID.fromString(rs.getString("TypePassID")));
		t.setName(rs.getString("Name"));
		t.setPrice(rs.getDouble("Price"));
		t.setLastUpdated(rs.getLong("TypePassLastUpdated"));

		return t;
	}

	public static void createTable(Connection con)
	{
		PreparedStatement ps = null;

		String sql = "CREATE TABLE IF NOT EXISTS `TypePass` ("
				+ "`TypePassID` varchar(36) NOT NULL DEFAULT '0',"
				+ "`Name` varchar(50) NOT NULL,"
				+ "`Price` double NOT NULL,"
				+ "`LastUpdated` bigint(14) NOT NULL," 
				+ "PRIMARY KEY (`TypePassID`)"
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