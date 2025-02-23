import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import javax.net.ssl.HttpsURLConnection;
import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("ap")
public class class10 {
	@ObfuscatedName("wj")
	static Iterator field40;
	@ObfuscatedName("at")
	final HttpsURLConnection field46;
	@ObfuscatedName("ah")
	@ObfuscatedSignature(
		descriptor = "Lqf;"
	)
	final class431 field39;
	@ObfuscatedName("ar")
	@ObfuscatedSignature(
		descriptor = "Laz;"
	)
	final class9 field41;
	@ObfuscatedName("ao")
	@ObfuscatedSignature(
		descriptor = "Lsd;"
	)
	class473 field42;
	@ObfuscatedName("ab")
	boolean field43;
	@ObfuscatedName("au")
	boolean field44;
	@ObfuscatedName("aa")
	@ObfuscatedGetter(
		intValue = -424565133
	)
	int field45;

	@ObfuscatedSignature(
		descriptor = "(Ljava/net/URL;Laz;Lqf;Z)V"
	)
	public class10(URL var1, class9 var2, class431 var3, boolean var4) throws IOException {
		this.field43 = false;
		this.field44 = false;
		this.field45 = 300000;
		if (!var2.method76()) {
			throw new UnsupportedEncodingException("Unsupported request method used " + var2.method75());
		} else {
			this.field46 = (HttpsURLConnection)var1.openConnection();
			if (!var4) {
				HttpsURLConnection var5 = this.field46;
				if (class15.field69 == null) {
					class15.field69 = new class15();
				}

				class15 var6 = class15.field69;
				var5.setSSLSocketFactory(var6);
			}

			this.field41 = var2;
			this.field39 = var3 != null ? var3 : new class431();
		}
	}

	@ObfuscatedSignature(
		descriptor = "(Ljava/net/URL;Laz;Z)V"
	)
	public class10(URL var1, class9 var2, boolean var3) throws IOException {
		this(var1, var2, new class431(), var3);
	}

	@ObfuscatedName("at")
	@ObfuscatedSignature(
		descriptor = "(I)Lqf;",
		garbageValue = "77865186"
	)
	public class431 method95() {
		return this.field39;
	}

	@ObfuscatedName("ah")
	@ObfuscatedSignature(
		descriptor = "(Lsd;I)V",
		garbageValue = "2039275921"
	)
	public void method92(class473 var1) {
		if (!this.field43) {
			if (var1 == null) {
				this.field39.method7955("Content-Type");
				this.field42 = null;
			} else {
				this.field42 = var1;
				if (this.field42.vmethod8594() != null) {
					this.field39.method7981(this.field42.vmethod8594());
				} else {
					this.field39.method7960();
				}

			}
		}
	}

	@ObfuscatedName("ar")
	@ObfuscatedSignature(
		descriptor = "(B)V",
		garbageValue = "6"
	)
	void method103() throws ProtocolException {
		if (!this.field43) {
			this.field46.setRequestMethod(this.field41.method75());
			this.field39.method7958(this.field46);
			if (this.field41.method74() && this.field42 != null) {
				this.field46.setDoOutput(true);
				ByteArrayOutputStream var1 = new ByteArrayOutputStream();

				try {
					var1.write(this.field42.vmethod8587());
					var1.writeTo(this.field46.getOutputStream());
				} catch (IOException var11) {
					var11.printStackTrace();
				} finally {
					try {
						var1.close();
					} catch (IOException var10) {
						var10.printStackTrace();
					}

				}
			}

			this.field46.setConnectTimeout(this.field45);
			this.field46.setInstanceFollowRedirects(this.field44);
			this.field43 = true;
		}
	}

	@ObfuscatedName("ao")
	@ObfuscatedSignature(
		descriptor = "(I)Z",
		garbageValue = "1613730505"
	)
	boolean method94() throws IOException {
		if (!this.field43) {
			this.method103();
		}

		this.field46.connect();
		return this.field46.getResponseCode() == -1;
	}

	@ObfuscatedName("ab")
	@ObfuscatedSignature(
		descriptor = "(I)Law;",
		garbageValue = "1005545851"
	)
	class20 method91() {
		try {
			if (!this.field43 || this.field46.getResponseCode() == -1) {
				return new class20("No REST response has been received yet.");
			}
		} catch (IOException var10) {
			this.field46.disconnect();
			return new class20("Error decoding REST response code: " + var10.getMessage());
		}

		class20 var3;
		try {
			class20 var1 = new class20(this.field46);
			return var1;
		} catch (IOException var8) {
			var3 = new class20("Error decoding REST response: " + var8.getMessage());
		} finally {
			this.field46.disconnect();
		}

		return var3;
	}

	@ObfuscatedName("at")
	@ObfuscatedSignature(
		descriptor = "(Ljava/lang/String;I)Ljava/io/File;",
		garbageValue = "1180339211"
	)
	@Export("getFile")
	static File getFile(String var0) {
		if (!FileSystem.FileSystem_hasPermissions) {
			throw new RuntimeException("");
		} else {
			File var1 = (File)FileSystem.FileSystem_cacheFiles.get(var0);
			if (var1 != null) {
				return var1;
			} else {
				File var2 = new File(FileSystem.FileSystem_cacheDir, var0);
				RandomAccessFile var3 = null;

				try {
					File var4 = new File(var2.getParent());
					if (!var4.exists()) {
						throw new RuntimeException("");
					} else {
						var3 = new RandomAccessFile(var2, "rw");
						int var5 = var3.read();
						var3.seek(0L);
						var3.write(var5);
						var3.seek(0L);
						var3.close();
						FileSystem.FileSystem_cacheFiles.put(var0, var2);
						return var2;
					}
				} catch (Exception var8) {
					try {
						if (var3 != null) {
							var3.close();
							var3 = null;
						}
					} catch (Exception var7) {
					}

					throw new RuntimeException();
				}
			}
		}
	}

	@ObfuscatedName("at")
	@ObfuscatedSignature(
		descriptor = "(III)I",
		garbageValue = "-841050646"
	)
	public static int method109(int var0, int var1) {
		return (var0 << 8) + var1;
	}

	@ObfuscatedName("aa")
	@ObfuscatedSignature(
		descriptor = "(Lol;Lol;Ljava/lang/String;Ljava/lang/String;B)Lpf;",
		garbageValue = "84"
	)
	public static Font method110(AbstractArchive var0, AbstractArchive var1, String var2, String var3) {
		if (!var0.isValidFileName(var2, var3)) {
			return null;
		} else {
			int var4 = var0.getGroupId(var2);
			int var5 = var0.getFileId(var4, var3);
			byte[] var8 = var0.takeFile(var4, var5);
			boolean var7;
			if (var8 == null) {
				var7 = false;
			} else {
				class162.SpriteBuffer_decode(var8);
				var7 = true;
			}

			Font var6;
			if (!var7) {
				var6 = null;
			} else {
				var6 = WorldMapArea.method4590(var1.takeFile(var4, var5));
			}

			return var6;
		}
	}

	@ObfuscatedName("az")
	@ObfuscatedSignature(
		descriptor = "(CS)Z",
		garbageValue = "128"
	)
	@Export("isCharAlphabetic")
	public static boolean isCharAlphabetic(char var0) {
		return var0 >= 'A' && var0 <= 'Z' || var0 >= 'a' && var0 <= 'z';
	}

	@ObfuscatedName("lv")
	@ObfuscatedSignature(
		descriptor = "(IIB)V",
		garbageValue = "10"
	)
	@Export("resumePauseWidget")
	static void resumePauseWidget(int var0, int var1) {
		PacketBufferNode var2 = class113.getPacketBufferNode(ClientPacket.RESUME_PAUSEBUTTON, Client.packetWriter.isaacCipher);
		var2.packetBuffer.writeIntME(var0);
		var2.packetBuffer.writeShortAddLE(var1);
		Client.packetWriter.addNode(var2);
	}
}
