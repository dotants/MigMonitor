/////////////////////////////////////////////////////////////////////////
// Project Web Services
/////////////////////////////////////////////////////////////////////////

package com.k2view.cdbms.usercode.lu.k2_ws.monitor;

import java.util.*;
import java.sql.*;
import java.math.*;
import java.io.*;

import com.k2view.fabric.common.mtable.MTable;
import com.k2view.fabric.common.mtable.MTables;

import com.k2view.cdbms.shared.*;
import com.k2view.cdbms.shared.user.WebServiceUserCode;
import com.k2view.cdbms.sync.*;
import com.k2view.cdbms.lut.*;
import com.k2view.cdbms.shared.utils.UserCodeDescribe.*;
import com.k2view.cdbms.shared.logging.LogEntry.*;
import com.k2view.cdbms.func.oracle.OracleToDate;
import com.k2view.cdbms.func.oracle.OracleRownum;
import com.k2view.fabric.api.endpoint.Endpoint.*;

import static com.k2view.cdbms.shared.utils.UserCodeDescribe.FunctionType.*;
import static com.k2view.cdbms.shared.user.ProductFunctions.*;
import static com.k2view.cdbms.usercode.common.SharedLogic.*;
import static com.k2view.cdbms.usercode.common.SharedGlobals.*;

@SuppressWarnings({"unused", "DefaultAnnotationParam"})
public class Logic extends WebServiceUserCode {


	@webService(path = "", verb = {MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE}, version = "1", isRaw = false, isCustomPayload = false, produce = {Produce.XML, Produce.JSON}, elevatedPermission = false)
	public static Object wsExtractStatus(@param(required=true) String execId) throws Exception {
		return fabric().fetch("broadway MigDummy.extractStatus execId='"+execId+"'");
	}


	@webService(path = "", verb = {MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE}, version = "1", isRaw = false, isCustomPayload = false, produce = {Produce.XML, Produce.JSON}, elevatedPermission = false)
	public static Object wsMonitorConfig() throws Exception {
		
		MTable mTable = MTables.get("mtMigMonitorConfig");
		Map<String,Object> key= new HashMap<>();
		key.put("Active Ind", "1");
		List<Map<String, Object>> mtData = mTable.mapsByKey(key);
		
		return mtData;
	}


	@desc("Get Execution Id value")
	@webService(path = "", verb = {MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE}, version = "1", isRaw = false, isCustomPayload = false, produce = {Produce.XML, Produce.JSON}, elevatedPermission = false)
	public static String wsExecId() throws Exception {
		String execId = "";
		
		Db.Rows rows = fabric().fetch("broadway MigDummy.getMigExecId");
		
		for (Db.Row row:rows) {
			execId = (String)row.cell(0);
			break;
		}
		
		return execId;
	}

	
	

	
}
