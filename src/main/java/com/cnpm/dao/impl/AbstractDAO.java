package com.cnpm.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.cnpm.dao.GenericDAO;
import com.cnpm.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String uRL="jdbc:mysql://localhost:3306/englishonline";
			String username = "root";
			String password = "admin123";
			return DriverManager.getConnection(uRL, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}

	@Override
	public List<T> query(String sql, RowMapper<T> mapRow, Object... parameters) {
		List<T> results = new ArrayList<>();
		Connection connection = null;
		PreparedStatement  statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			setParameter(statement, parameters);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				results.add(mapRow.mapRow(resultSet));
			}
			return results;
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if(connection!=null) {
					connection.close();
				}
				if(statement!=null) {
					statement.close();
				}
				if(resultSet!=null) {
					resultSet.close();
				}
			} catch(SQLException e) {
				return null;
			}
		}
	}

	@Override
	public Long them(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement  statement = null;
		ResultSet resultSet = null;
		Long id = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameter(statement, parameters);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			while(resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		} catch (SQLException e) {
			if(connection!=null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					return null;
				}
			}
			return null;
		} finally {
			try {
				if(connection!=null) {
					connection.close();
				}
				if(statement!=null) {
					statement.close();
				}
				if(resultSet!=null) {
					resultSet.close();
				}
			} catch(SQLException e) {
				return null;
			}
		}
	}

	@Override
	public void capNhat(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement  statement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			setParameter(statement, parameters);
			statement.executeUpdate();
			connection.commit();

		} catch (SQLException e) {
			if(connection!=null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					return;
				}
			}
			return;
		} finally {
			try {
				if(connection!=null) {
					connection.close();
				}
				if(statement!=null) {
					statement.close();
				}
			} catch(SQLException e) {
				return;
			}
		}
		
	}

	@Override
	public int dem(String sql, Object... parameters) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			int count = 0;
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			setParameter(statement, parameters);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				count = resultSet.getInt(1);
			}
			return count;
		} catch(SQLException e) {
			return 0;
		} finally {
			try {
				if(connection!=null) {
					connection.close();
				}
				if(statement!=null) {
					statement.close();
				}
				if(resultSet!=null) {
					resultSet.close();
				}
			} catch(SQLException e) {
				return 0;
			}
		}
	}
	
	@SuppressWarnings("null")
	public void setParameter(PreparedStatement statement ,Object... parameters) {
		try {
			for(int i=0;i<parameters.length;i++) {
				Object parameter = parameters[i];
				int index = i+1;
				if(parameter instanceof Integer) {
					statement.setInt(index, (Integer) parameter);
				}
				else if(parameter instanceof Long) {
					statement.setLong(index, (Long) parameter);
				}
				else if(parameter instanceof String) {
					statement.setString(index, (String) parameter);
				}
				else if(parameter instanceof Timestamp){
					statement.setTimestamp(index, (Timestamp) parameter);
				}
				else if(parameter instanceof Boolean) {
					statement.setBoolean(index, (Boolean) parameter);
				}
				else if(parameter == null) {
					statement.setString(index,"");
				}
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return;
		}
	}
}
