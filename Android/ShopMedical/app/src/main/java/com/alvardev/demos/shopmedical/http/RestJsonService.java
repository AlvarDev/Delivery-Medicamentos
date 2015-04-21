package com.alvardev.demos.shopmedical.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;


public class RestJsonService extends IntentService{

	public static final String LLAVE_URL = "URL";
	public static final String LLAVE_METODO_PETICION = "METODO";
	public static final String LLAVE_CONTENIDO_PETICION = "CONTENIDO_PETICION";
	public static final String LLAVE_CONTENIDO_RESPUESTA = "CONTENIDO_RESPUESTA";
	public static final String LLAVE_RECIBIDOR = "RECIBIDOR";
	public static final String LLAVE_TIPO_ACCION = "TIPO_ACCION";

	private static final String TAG = RestJsonService.class.getName();

	public RestJsonService() {
		super("Servicio Json Rest");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		Log.d(TAG, "Iniciando servicio");
		String url = null;
		String datos = null;
		int metodo = 0;
		int tipoAccion = 0;
		HttpResult resultado = null;

		Bundle parametros = intent.getExtras();

		url = parametros.getString(LLAVE_URL);
		datos = parametros.getString(LLAVE_CONTENIDO_PETICION);
		metodo = parametros.getInt(LLAVE_METODO_PETICION);
		tipoAccion = parametros.getInt(LLAVE_TIPO_ACCION);

		Log.d(TAG, "Llamada a la url " + url);
		Log.d(TAG, "Llamada con datos " + datos);

		ResultReceiver recibidor = parametros.getParcelable(LLAVE_RECIBIDOR);

		HttpMethod metodoEnum = HttpMethod.forValue(metodo);

		switch (metodoEnum) {
		case POST:
			resultado = postJson(datos, url);
			break;
		case GET:
			resultado = getJson(url);
			break;
		case PUT:
			resultado = putJson(datos, url);
			break;	
		case DELETE:
			resultado = deleteJson(datos,url);
			break;
		default:
			break;
		}
		Log.d(TAG, "Respuesta codigo : " + resultado.getStatusCode());

		Log.d(TAG, "Respuesta data : " + resultado.getData());

		Bundle datosResultado = new Bundle();
		datosResultado
				.putString(LLAVE_CONTENIDO_RESPUESTA, resultado.getData());
		datosResultado.putInt(LLAVE_TIPO_ACCION, tipoAccion);

		recibidor.send(resultado.getStatusCode(), datosResultado);
		recibidor = null;
	}

	private HttpResult postJson(String bodyRequest, String url) {

		HttpPost httpPost = new HttpPost(url);
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 20000);
		HttpConnectionParams.setSoTimeout(httpParams, 25000);
		HttpClient httpClient = new DefaultHttpClient(httpParams);

		String data = null;
		int status = 0;

		StringEntity se;
		try {
			se = new StringEntity(bodyRequest, "UTF8");
			se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "text/json"));
			httpPost.setEntity(se);

			HttpResponse response = httpClient.execute(httpPost);
			status = response.getStatusLine().getStatusCode();

			HttpEntity entity = response.getEntity();
			String respuesta = EntityUtils.toString(entity);
			data = respuesta;

		} catch (ClientProtocolException e) {
			status = HttpCode.ERROR.getCode();
			Log.e(TAG, "Error protocolo");
			Log.e(TAG, getErrorMessage(e, "Error al realizar la petici�n"));
		} catch (IOException e) {
			Log.e(TAG, "Error timeout");
			Log.e(TAG, getErrorMessage(e, "Timeout sucedio"));
			status = HttpCode.TIMEOUT.getCode();
		} catch (Exception e) {
			Log.e(TAG, "Error general");

			Log.e(TAG,
					getErrorMessage(e,
							"Error generico sucedio al realizar la petici�n"));
			status = HttpCode.ERROR.getCode();
		}

		return new HttpResult(data, status);
	}

	private HttpResult getJson(String url) {

		HttpGet httpGet = new HttpGet(url);
		

		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 20000);
		HttpConnectionParams.setSoTimeout(httpParams, 25000);
		HttpClient httpClient = new DefaultHttpClient(httpParams);

		String data = null;
		int status = 0;

		try {

			HttpResponse response = httpClient.execute(httpGet);

			HttpEntity entity = response.getEntity();
			Log.d(TAG, "entidad obtenida");

			String respuesta = EntityUtils.toString(entity);
			Log.d(TAG, "respuesta obtenida");

			status = response.getStatusLine().getStatusCode();
			Log.d(TAG, "Estado obtenido");

			data = respuesta;

		} catch (ClientProtocolException e) {
			Log.e(TAG, "Error protocolo");
			status = HttpCode.ERROR.getCode();
			Log.e(TAG, getErrorMessage(e, "Error al realizar la petici�n"));
		} catch (IOException e) {
			Log.e(TAG, "Error timeout");
			Log.e(TAG, getErrorMessage(e, "Timeout sucedio"));
			status = HttpCode.TIMEOUT.getCode();
		} catch (Exception e) {
			Log.e(TAG, "Error general");
			Log.e(TAG,
					getErrorMessage(e,
							"Error generico sucedio al realizar la petici�n"));
			status = HttpCode.ERROR.getCode();
		}

		return new HttpResult(data, status);
	}
	
	private HttpResult putJson(String bodyRequest, String url) {

		HttpPut httpPut = new HttpPut(url);
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 20000);
		HttpConnectionParams.setSoTimeout(httpParams, 25000);
		HttpClient httpClient = new DefaultHttpClient(httpParams);

		String data = null;
		int status = 0;

		StringEntity se;
		try {
			se = new StringEntity(bodyRequest, "UTF8");
			se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "text/json"));
			httpPut.setEntity(se);

			HttpResponse response = httpClient.execute(httpPut);
			status = response.getStatusLine().getStatusCode();

			HttpEntity entity = response.getEntity();
			String respuesta = EntityUtils.toString(entity);
			data = respuesta;

		} catch (ClientProtocolException e) {
			status = HttpCode.ERROR.getCode();
			Log.e(TAG, "Error protocolo");
			Log.e(TAG, getErrorMessage(e, "Error al realizar la petici�n"));
		} catch (IOException e) {
			Log.e(TAG, "Error timeout");
			Log.e(TAG, getErrorMessage(e, "Timeout sucedio"));
			status = HttpCode.TIMEOUT.getCode();
		} catch (Exception e) {
			Log.e(TAG, "Error general");

			Log.e(TAG,
					getErrorMessage(e,
							"Error generico sucedio al realizar la petici�n"));
			status = HttpCode.ERROR.getCode();
		}

		return new HttpResult(data, status);
	}
	
	private HttpResult deleteJson(String bodyRequest, String url) {

		HttpDelete httpDelete = new HttpDelete(url);
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 20000);
		HttpConnectionParams.setSoTimeout(httpParams, 25000);
		HttpClient httpClient = new DefaultHttpClient(httpParams);

		String data = null;
		int status = 0;

		try {
			

			HttpResponse response = httpClient.execute(httpDelete);
			status = response.getStatusLine().getStatusCode();

			HttpEntity entity = response.getEntity();
			String respuesta = EntityUtils.toString(entity);
			data = respuesta;

		} catch (ClientProtocolException e) {
			status = HttpCode.ERROR.getCode();
			Log.e(TAG, "Error protocolo");
			Log.e(TAG, getErrorMessage(e, "Error al realizar la petici�n"));
		} catch (IOException e) {
			Log.e(TAG, "Error timeout");
			Log.e(TAG, getErrorMessage(e, "Timeout sucedio"));
			status = HttpCode.TIMEOUT.getCode();
		} catch (Exception e) {
			Log.e(TAG, "Error general");

			Log.e(TAG,
					getErrorMessage(e,
							"Error generico sucedio al realizar la petici�n"));
			status = HttpCode.ERROR.getCode();
		}

		return new HttpResult(data, status);
	}
	
	private String getErrorMessage(Exception e, String defaultMessage) {
		String message = defaultMessage;
		if (e != null && e.getMessage() != null) {
			message = e.getMessage();
		}
		return message;
	}
	
	
}
