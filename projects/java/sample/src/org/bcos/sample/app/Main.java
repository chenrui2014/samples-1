package org.bcos.sample.app;

import java.math.BigInteger;

import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

public class Main {

	public static void main(String[] args) {
		BcosApp app = new BcosApp();
		
		//1. load config of the sample application
		BcosConfig configure = app.loadConfig();
		if (configure == null) {
			System.err.println("error in load configure, init failed  !!!");
			return;
		}
		System.out.println("load configure successully ! ");
		
		//2. deploy the contract to BCOS blockchain
		Address address = app.deployContract();
		if (address == null) {
			System.err.println("error in deploy contract !!!");
			return;
		}
		System.out.println("deploy SimpleStorage success, address: " + address.toString());
		
		//3. send Raw Transaction to blockchain
//		Address address = null;
//		if (configure.getRpc_port() == 8545)
//			address = new Address("0x8d934ebd9e0257fae31a7ff1211499816abc1288");
//		else
//			address = new Address("0x8d211db7c5cef5620820b229001c0b239f36b765");
		
		//app.setDeployAddress(address);
		TransactionReceipt receipt = app.executeTransaction(address);
		if (receipt == null) {
			System.err.println("error in executeTransaction  !!!");
			return;
		}
		System.out.println("execute SimpleStorage transaction success, TxHash: " + receipt.getTransactionHash());
		
		//4. retrieve the log
		String logInfo = app.getLogInfo(receipt);
		System.out.println("get the log: " + logInfo);
		
		//4. send call jsonrpc to blockchain
		BigInteger value = app.executeCall(address);
		System.out.println("the call value: " + value.intValue());
	}

}
