namespace WindowsFormsApplication1
{
    partial class frmBlackjack
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(frmBlackjack));
            this.btnPlay = new System.Windows.Forms.Button();
            this.btnHit = new System.Windows.Forms.Button();
            this.btnStay = new System.Windows.Forms.Button();
            this.btnQuit = new System.Windows.Forms.Button();
            this.lblDScore = new System.Windows.Forms.Label();
            this.lblPScore = new System.Windows.Forms.Label();
            this.pbxDealer = new System.Windows.Forms.PictureBox();
            this.pbxPlayer = new System.Windows.Forms.PictureBox();
            this.pbxPBoard = new System.Windows.Forms.PictureBox();
            this.pbxDBoard = new System.Windows.Forms.PictureBox();
            this.pbxPScore = new System.Windows.Forms.PictureBox();
            this.pbxDScore = new System.Windows.Forms.PictureBox();
            this.pbxD1 = new System.Windows.Forms.PictureBox();
            this.pbxD2 = new System.Windows.Forms.PictureBox();
            this.pbxD3 = new System.Windows.Forms.PictureBox();
            this.pbxD4 = new System.Windows.Forms.PictureBox();
            this.pbxD5 = new System.Windows.Forms.PictureBox();
            this.pbxD6 = new System.Windows.Forms.PictureBox();
            this.pbxD7 = new System.Windows.Forms.PictureBox();
            this.pbxD8 = new System.Windows.Forms.PictureBox();
            this.pbxP1 = new System.Windows.Forms.PictureBox();
            this.pbxP2 = new System.Windows.Forms.PictureBox();
            this.pbxP3 = new System.Windows.Forms.PictureBox();
            this.pbxP4 = new System.Windows.Forms.PictureBox();
            this.pbxP5 = new System.Windows.Forms.PictureBox();
            this.pbxP6 = new System.Windows.Forms.PictureBox();
            this.pbxP7 = new System.Windows.Forms.PictureBox();
            this.pbxP8 = new System.Windows.Forms.PictureBox();
            this.pbxD9 = new System.Windows.Forms.PictureBox();
            this.pbxD10 = new System.Windows.Forms.PictureBox();
            this.pbxD11 = new System.Windows.Forms.PictureBox();
            this.pbxP9 = new System.Windows.Forms.PictureBox();
            this.pbxP10 = new System.Windows.Forms.PictureBox();
            this.pbxP11 = new System.Windows.Forms.PictureBox();
            this.btnDeal = new System.Windows.Forms.Button();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.lblWins = new System.Windows.Forms.Label();
            this.lblTies = new System.Windows.Forms.Label();
            this.lblLosses = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.pbxDealer)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxPlayer)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxPBoard)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxDBoard)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxPScore)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxDScore)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD3)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD4)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD5)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD6)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD7)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD8)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP3)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP4)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP5)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP6)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP7)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP8)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD9)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD10)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD11)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP9)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP10)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP11)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // btnPlay
            // 
            this.btnPlay.BackgroundImage = global::WindowsFormsApplication1.Properties.Resources.Button;
            this.btnPlay.Location = new System.Drawing.Point(433, 324);
            this.btnPlay.Name = "btnPlay";
            this.btnPlay.Size = new System.Drawing.Size(141, 65);
            this.btnPlay.TabIndex = 4;
            this.btnPlay.Text = "&PLAY";
            this.btnPlay.UseVisualStyleBackColor = true;
            this.btnPlay.Click += new System.EventHandler(this.btnPlay_Click);
            // 
            // btnHit
            // 
            this.btnHit.BackgroundImage = global::WindowsFormsApplication1.Properties.Resources.Button;
            this.btnHit.Enabled = false;
            this.btnHit.Location = new System.Drawing.Point(433, 324);
            this.btnHit.Name = "btnHit";
            this.btnHit.Size = new System.Drawing.Size(141, 65);
            this.btnHit.TabIndex = 5;
            this.btnHit.Text = "&HIT";
            this.btnHit.UseVisualStyleBackColor = true;
            this.btnHit.Click += new System.EventHandler(this.btnHit_Click);
            // 
            // btnStay
            // 
            this.btnStay.BackColor = System.Drawing.Color.Transparent;
            this.btnStay.BackgroundImage = global::WindowsFormsApplication1.Properties.Resources.Button;
            this.btnStay.Enabled = false;
            this.btnStay.Location = new System.Drawing.Point(580, 324);
            this.btnStay.Name = "btnStay";
            this.btnStay.Size = new System.Drawing.Size(141, 65);
            this.btnStay.TabIndex = 6;
            this.btnStay.Text = "&STAY";
            this.btnStay.UseVisualStyleBackColor = false;
            this.btnStay.Click += new System.EventHandler(this.btnStay_Click);
            // 
            // btnQuit
            // 
            this.btnQuit.BackColor = System.Drawing.Color.Transparent;
            this.btnQuit.BackgroundImage = global::WindowsFormsApplication1.Properties.Resources.Button;
            this.btnQuit.Location = new System.Drawing.Point(580, 324);
            this.btnQuit.Name = "btnQuit";
            this.btnQuit.Size = new System.Drawing.Size(141, 65);
            this.btnQuit.TabIndex = 7;
            this.btnQuit.Text = "&QUIT";
            this.btnQuit.UseVisualStyleBackColor = false;
            this.btnQuit.Click += new System.EventHandler(this.btnQuit_Click);
            // 
            // lblDScore
            // 
            this.lblDScore.AutoSize = true;
            this.lblDScore.BackColor = System.Drawing.Color.Silver;
            this.lblDScore.Font = new System.Drawing.Font("Broadway", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblDScore.Location = new System.Drawing.Point(74, 252);
            this.lblDScore.Name = "lblDScore";
            this.lblDScore.Size = new System.Drawing.Size(80, 19);
            this.lblDScore.TabIndex = 8;
            this.lblDScore.Text = "Score : 0";
            // 
            // lblPScore
            // 
            this.lblPScore.AutoSize = true;
            this.lblPScore.BackColor = System.Drawing.Color.Silver;
            this.lblPScore.Font = new System.Drawing.Font("Broadway", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblPScore.Location = new System.Drawing.Point(74, 452);
            this.lblPScore.Name = "lblPScore";
            this.lblPScore.Size = new System.Drawing.Size(80, 19);
            this.lblPScore.TabIndex = 9;
            this.lblPScore.Text = "Score : 0";
            // 
            // pbxDealer
            // 
            this.pbxDealer.BackColor = System.Drawing.Color.Transparent;
            this.pbxDealer.Image = global::WindowsFormsApplication1.Properties.Resources.Dealer;
            this.pbxDealer.Location = new System.Drawing.Point(25, 19);
            this.pbxDealer.MaximumSize = new System.Drawing.Size(190, 191);
            this.pbxDealer.MinimumSize = new System.Drawing.Size(190, 191);
            this.pbxDealer.Name = "pbxDealer";
            this.pbxDealer.Size = new System.Drawing.Size(190, 191);
            this.pbxDealer.TabIndex = 3;
            this.pbxDealer.TabStop = false;
            // 
            // pbxPlayer
            // 
            this.pbxPlayer.BackColor = System.Drawing.Color.Transparent;
            this.pbxPlayer.BackgroundImage = global::WindowsFormsApplication1.Properties.Resources.clammy_zell_ll_191x191E;
            this.pbxPlayer.Location = new System.Drawing.Point(25, 512);
            this.pbxPlayer.MaximumSize = new System.Drawing.Size(190, 191);
            this.pbxPlayer.MinimumSize = new System.Drawing.Size(190, 191);
            this.pbxPlayer.Name = "pbxPlayer";
            this.pbxPlayer.Size = new System.Drawing.Size(190, 191);
            this.pbxPlayer.TabIndex = 2;
            this.pbxPlayer.TabStop = false;
            // 
            // pbxPBoard
            // 
            this.pbxPBoard.BackColor = System.Drawing.Color.Transparent;
            this.pbxPBoard.BackgroundImage = global::WindowsFormsApplication1.Properties.Resources.CardPanel;
            this.pbxPBoard.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.pbxPBoard.Location = new System.Drawing.Point(232, 557);
            this.pbxPBoard.Name = "pbxPBoard";
            this.pbxPBoard.Size = new System.Drawing.Size(694, 100);
            this.pbxPBoard.TabIndex = 10;
            this.pbxPBoard.TabStop = false;
            // 
            // pbxDBoard
            // 
            this.pbxDBoard.BackColor = System.Drawing.Color.Transparent;
            this.pbxDBoard.BackgroundImage = global::WindowsFormsApplication1.Properties.Resources.CardPanel;
            this.pbxDBoard.Location = new System.Drawing.Point(232, 64);
            this.pbxDBoard.Name = "pbxDBoard";
            this.pbxDBoard.Size = new System.Drawing.Size(694, 100);
            this.pbxDBoard.TabIndex = 11;
            this.pbxDBoard.TabStop = false;
            // 
            // pbxPScore
            // 
            this.pbxPScore.BackColor = System.Drawing.Color.Transparent;
            this.pbxPScore.BackgroundImage = global::WindowsFormsApplication1.Properties.Resources.ScorePanel;
            this.pbxPScore.Location = new System.Drawing.Point(58, 413);
            this.pbxPScore.Name = "pbxPScore";
            this.pbxPScore.Size = new System.Drawing.Size(122, 94);
            this.pbxPScore.TabIndex = 12;
            this.pbxPScore.TabStop = false;
            // 
            // pbxDScore
            // 
            this.pbxDScore.BackColor = System.Drawing.Color.Transparent;
            this.pbxDScore.BackgroundImage = global::WindowsFormsApplication1.Properties.Resources.ScorePanel;
            this.pbxDScore.Location = new System.Drawing.Point(58, 215);
            this.pbxDScore.Name = "pbxDScore";
            this.pbxDScore.Size = new System.Drawing.Size(122, 94);
            this.pbxDScore.TabIndex = 13;
            this.pbxDScore.TabStop = false;
            // 
            // pbxD1
            // 
            this.pbxD1.BackColor = System.Drawing.Color.Transparent;
            this.pbxD1.Location = new System.Drawing.Point(242, 52);
            this.pbxD1.Name = "pbxD1";
            this.pbxD1.Size = new System.Drawing.Size(79, 123);
            this.pbxD1.TabIndex = 14;
            this.pbxD1.TabStop = false;
            this.pbxD1.Visible = false;
            // 
            // pbxD2
            // 
            this.pbxD2.BackColor = System.Drawing.Color.Transparent;
            this.pbxD2.Location = new System.Drawing.Point(327, 52);
            this.pbxD2.Name = "pbxD2";
            this.pbxD2.Size = new System.Drawing.Size(79, 123);
            this.pbxD2.TabIndex = 15;
            this.pbxD2.TabStop = false;
            this.pbxD2.Visible = false;
            // 
            // pbxD3
            // 
            this.pbxD3.BackColor = System.Drawing.Color.Transparent;
            this.pbxD3.Location = new System.Drawing.Point(412, 52);
            this.pbxD3.Name = "pbxD3";
            this.pbxD3.Size = new System.Drawing.Size(79, 123);
            this.pbxD3.TabIndex = 16;
            this.pbxD3.TabStop = false;
            this.pbxD3.Visible = false;
            // 
            // pbxD4
            // 
            this.pbxD4.BackColor = System.Drawing.Color.Transparent;
            this.pbxD4.Location = new System.Drawing.Point(497, 52);
            this.pbxD4.Name = "pbxD4";
            this.pbxD4.Size = new System.Drawing.Size(79, 123);
            this.pbxD4.TabIndex = 17;
            this.pbxD4.TabStop = false;
            this.pbxD4.Visible = false;
            // 
            // pbxD5
            // 
            this.pbxD5.BackColor = System.Drawing.Color.Transparent;
            this.pbxD5.Location = new System.Drawing.Point(582, 52);
            this.pbxD5.Name = "pbxD5";
            this.pbxD5.Size = new System.Drawing.Size(79, 123);
            this.pbxD5.TabIndex = 18;
            this.pbxD5.TabStop = false;
            this.pbxD5.Visible = false;
            // 
            // pbxD6
            // 
            this.pbxD6.BackColor = System.Drawing.Color.Transparent;
            this.pbxD6.Location = new System.Drawing.Point(667, 52);
            this.pbxD6.Name = "pbxD6";
            this.pbxD6.Size = new System.Drawing.Size(79, 123);
            this.pbxD6.TabIndex = 19;
            this.pbxD6.TabStop = false;
            this.pbxD6.Visible = false;
            // 
            // pbxD7
            // 
            this.pbxD7.BackColor = System.Drawing.Color.Transparent;
            this.pbxD7.Location = new System.Drawing.Point(752, 52);
            this.pbxD7.Name = "pbxD7";
            this.pbxD7.Size = new System.Drawing.Size(79, 123);
            this.pbxD7.TabIndex = 20;
            this.pbxD7.TabStop = false;
            this.pbxD7.Visible = false;
            // 
            // pbxD8
            // 
            this.pbxD8.BackColor = System.Drawing.Color.Transparent;
            this.pbxD8.Location = new System.Drawing.Point(837, 52);
            this.pbxD8.Name = "pbxD8";
            this.pbxD8.Size = new System.Drawing.Size(79, 123);
            this.pbxD8.TabIndex = 21;
            this.pbxD8.TabStop = false;
            this.pbxD8.Visible = false;
            // 
            // pbxP1
            // 
            this.pbxP1.BackColor = System.Drawing.Color.Transparent;
            this.pbxP1.Location = new System.Drawing.Point(242, 546);
            this.pbxP1.Name = "pbxP1";
            this.pbxP1.Size = new System.Drawing.Size(79, 123);
            this.pbxP1.TabIndex = 22;
            this.pbxP1.TabStop = false;
            this.pbxP1.Visible = false;
            // 
            // pbxP2
            // 
            this.pbxP2.BackColor = System.Drawing.Color.Transparent;
            this.pbxP2.Location = new System.Drawing.Point(327, 546);
            this.pbxP2.Name = "pbxP2";
            this.pbxP2.Size = new System.Drawing.Size(79, 123);
            this.pbxP2.TabIndex = 23;
            this.pbxP2.TabStop = false;
            this.pbxP2.Visible = false;
            // 
            // pbxP3
            // 
            this.pbxP3.BackColor = System.Drawing.Color.Transparent;
            this.pbxP3.Location = new System.Drawing.Point(412, 546);
            this.pbxP3.Name = "pbxP3";
            this.pbxP3.Size = new System.Drawing.Size(79, 123);
            this.pbxP3.TabIndex = 24;
            this.pbxP3.TabStop = false;
            this.pbxP3.Visible = false;
            // 
            // pbxP4
            // 
            this.pbxP4.BackColor = System.Drawing.Color.Transparent;
            this.pbxP4.Location = new System.Drawing.Point(497, 546);
            this.pbxP4.Name = "pbxP4";
            this.pbxP4.Size = new System.Drawing.Size(79, 123);
            this.pbxP4.TabIndex = 25;
            this.pbxP4.TabStop = false;
            this.pbxP4.Visible = false;
            // 
            // pbxP5
            // 
            this.pbxP5.BackColor = System.Drawing.Color.Transparent;
            this.pbxP5.Location = new System.Drawing.Point(582, 546);
            this.pbxP5.Name = "pbxP5";
            this.pbxP5.Size = new System.Drawing.Size(79, 123);
            this.pbxP5.TabIndex = 26;
            this.pbxP5.TabStop = false;
            this.pbxP5.Visible = false;
            // 
            // pbxP6
            // 
            this.pbxP6.BackColor = System.Drawing.Color.Transparent;
            this.pbxP6.Location = new System.Drawing.Point(667, 546);
            this.pbxP6.Name = "pbxP6";
            this.pbxP6.Size = new System.Drawing.Size(79, 123);
            this.pbxP6.TabIndex = 27;
            this.pbxP6.TabStop = false;
            this.pbxP6.Visible = false;
            // 
            // pbxP7
            // 
            this.pbxP7.BackColor = System.Drawing.Color.Transparent;
            this.pbxP7.Location = new System.Drawing.Point(752, 546);
            this.pbxP7.Name = "pbxP7";
            this.pbxP7.Size = new System.Drawing.Size(79, 123);
            this.pbxP7.TabIndex = 28;
            this.pbxP7.TabStop = false;
            this.pbxP7.Visible = false;
            // 
            // pbxP8
            // 
            this.pbxP8.BackColor = System.Drawing.Color.Transparent;
            this.pbxP8.Location = new System.Drawing.Point(837, 546);
            this.pbxP8.Name = "pbxP8";
            this.pbxP8.Size = new System.Drawing.Size(79, 123);
            this.pbxP8.TabIndex = 29;
            this.pbxP8.TabStop = false;
            this.pbxP8.Visible = false;
            // 
            // pbxD9
            // 
            this.pbxD9.BackColor = System.Drawing.Color.Transparent;
            this.pbxD9.Location = new System.Drawing.Point(454, 181);
            this.pbxD9.Name = "pbxD9";
            this.pbxD9.Size = new System.Drawing.Size(79, 123);
            this.pbxD9.TabIndex = 30;
            this.pbxD9.TabStop = false;
            this.pbxD9.Visible = false;
            // 
            // pbxD10
            // 
            this.pbxD10.BackColor = System.Drawing.Color.Transparent;
            this.pbxD10.Location = new System.Drawing.Point(539, 181);
            this.pbxD10.Name = "pbxD10";
            this.pbxD10.Size = new System.Drawing.Size(79, 123);
            this.pbxD10.TabIndex = 31;
            this.pbxD10.TabStop = false;
            this.pbxD10.Visible = false;
            // 
            // pbxD11
            // 
            this.pbxD11.BackColor = System.Drawing.Color.Transparent;
            this.pbxD11.Location = new System.Drawing.Point(624, 181);
            this.pbxD11.Name = "pbxD11";
            this.pbxD11.Size = new System.Drawing.Size(79, 123);
            this.pbxD11.TabIndex = 32;
            this.pbxD11.TabStop = false;
            this.pbxD11.Visible = false;
            // 
            // pbxP9
            // 
            this.pbxP9.BackColor = System.Drawing.Color.Transparent;
            this.pbxP9.Location = new System.Drawing.Point(454, 417);
            this.pbxP9.Name = "pbxP9";
            this.pbxP9.Size = new System.Drawing.Size(79, 123);
            this.pbxP9.TabIndex = 33;
            this.pbxP9.TabStop = false;
            this.pbxP9.Visible = false;
            // 
            // pbxP10
            // 
            this.pbxP10.BackColor = System.Drawing.Color.Transparent;
            this.pbxP10.Location = new System.Drawing.Point(539, 417);
            this.pbxP10.Name = "pbxP10";
            this.pbxP10.Size = new System.Drawing.Size(79, 123);
            this.pbxP10.TabIndex = 34;
            this.pbxP10.TabStop = false;
            this.pbxP10.Visible = false;
            // 
            // pbxP11
            // 
            this.pbxP11.BackColor = System.Drawing.Color.Transparent;
            this.pbxP11.Location = new System.Drawing.Point(624, 417);
            this.pbxP11.Name = "pbxP11";
            this.pbxP11.Size = new System.Drawing.Size(79, 123);
            this.pbxP11.TabIndex = 35;
            this.pbxP11.TabStop = false;
            this.pbxP11.Visible = false;
            // 
            // btnDeal
            // 
            this.btnDeal.BackColor = System.Drawing.Color.Transparent;
            this.btnDeal.BackgroundImage = global::WindowsFormsApplication1.Properties.Resources.Button;
            this.btnDeal.Location = new System.Drawing.Point(286, 324);
            this.btnDeal.Name = "btnDeal";
            this.btnDeal.Size = new System.Drawing.Size(141, 65);
            this.btnDeal.TabIndex = 36;
            this.btnDeal.Text = "&DEAL";
            this.btnDeal.UseVisualStyleBackColor = false;
            this.btnDeal.Visible = false;
            this.btnDeal.Click += new System.EventHandler(this.btnDeal_Click);
            // 
            // pictureBox1
            // 
            this.pictureBox1.BackColor = System.Drawing.Color.Transparent;
            this.pictureBox1.BackgroundImage = global::WindowsFormsApplication1.Properties.Resources.ScorePanel;
            this.pictureBox1.Location = new System.Drawing.Point(58, 314);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(122, 94);
            this.pictureBox1.TabIndex = 38;
            this.pictureBox1.TabStop = false;
            // 
            // lblWins
            // 
            this.lblWins.AutoSize = true;
            this.lblWins.BackColor = System.Drawing.Color.Silver;
            this.lblWins.Font = new System.Drawing.Font("Broadway", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblWins.Location = new System.Drawing.Point(96, 339);
            this.lblWins.Name = "lblWins";
            this.lblWins.Size = new System.Drawing.Size(47, 12);
            this.lblWins.TabIndex = 39;
            this.lblWins.Text = "Wins: 0";
            // 
            // lblTies
            // 
            this.lblTies.AutoSize = true;
            this.lblTies.BackColor = System.Drawing.Color.Silver;
            this.lblTies.Font = new System.Drawing.Font("Broadway", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblTies.Location = new System.Drawing.Point(98, 356);
            this.lblTies.Name = "lblTies";
            this.lblTies.Size = new System.Drawing.Size(43, 12);
            this.lblTies.TabIndex = 40;
            this.lblTies.Text = "Ties: 0";
            // 
            // lblLosses
            // 
            this.lblLosses.AutoSize = true;
            this.lblLosses.BackColor = System.Drawing.Color.Silver;
            this.lblLosses.Font = new System.Drawing.Font("Broadway", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblLosses.Location = new System.Drawing.Point(93, 372);
            this.lblLosses.Name = "lblLosses";
            this.lblLosses.Size = new System.Drawing.Size(55, 12);
            this.lblLosses.TabIndex = 41;
            this.lblLosses.Text = "Losses: 0";
            // 
            // frmBlackjack
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.ControlLight;
            this.BackgroundImage = global::WindowsFormsApplication1.Properties.Resources.Blackjack_Board;
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Center;
            this.ClientSize = new System.Drawing.Size(955, 720);
            this.Controls.Add(this.lblLosses);
            this.Controls.Add(this.lblTies);
            this.Controls.Add(this.lblWins);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.btnDeal);
            this.Controls.Add(this.pbxP11);
            this.Controls.Add(this.pbxP10);
            this.Controls.Add(this.pbxP9);
            this.Controls.Add(this.pbxD11);
            this.Controls.Add(this.pbxD10);
            this.Controls.Add(this.pbxD9);
            this.Controls.Add(this.pbxP8);
            this.Controls.Add(this.pbxP7);
            this.Controls.Add(this.pbxP6);
            this.Controls.Add(this.pbxP5);
            this.Controls.Add(this.pbxP4);
            this.Controls.Add(this.pbxP3);
            this.Controls.Add(this.pbxP2);
            this.Controls.Add(this.pbxP1);
            this.Controls.Add(this.pbxD8);
            this.Controls.Add(this.pbxD7);
            this.Controls.Add(this.pbxD6);
            this.Controls.Add(this.pbxD5);
            this.Controls.Add(this.pbxD4);
            this.Controls.Add(this.pbxD3);
            this.Controls.Add(this.pbxD2);
            this.Controls.Add(this.pbxD1);
            this.Controls.Add(this.pbxDBoard);
            this.Controls.Add(this.pbxPBoard);
            this.Controls.Add(this.pbxDealer);
            this.Controls.Add(this.lblPScore);
            this.Controls.Add(this.lblDScore);
            this.Controls.Add(this.btnQuit);
            this.Controls.Add(this.btnStay);
            this.Controls.Add(this.btnPlay);
            this.Controls.Add(this.pbxPlayer);
            this.Controls.Add(this.pbxDScore);
            this.Controls.Add(this.pbxPScore);
            this.Controls.Add(this.btnHit);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximumSize = new System.Drawing.Size(971, 758);
            this.MinimumSize = new System.Drawing.Size(971, 758);
            this.Name = "frmBlackjack";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Blackjack";
            ((System.ComponentModel.ISupportInitialize)(this.pbxDealer)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxPlayer)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxPBoard)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxDBoard)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxPScore)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxDScore)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD3)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD4)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD5)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD6)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD7)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD8)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP3)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP4)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP5)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP6)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP7)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP8)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD9)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD10)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxD11)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP9)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP10)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxP11)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox pbxPlayer;
        private System.Windows.Forms.PictureBox pbxDealer;
        private System.Windows.Forms.Button btnPlay;
        private System.Windows.Forms.Button btnHit;
        private System.Windows.Forms.Button btnStay;
        private System.Windows.Forms.Button btnQuit;
        private System.Windows.Forms.Label lblDScore;
        private System.Windows.Forms.Label lblPScore;
        private System.Windows.Forms.PictureBox pbxPBoard;
        private System.Windows.Forms.PictureBox pbxDBoard;
        private System.Windows.Forms.PictureBox pbxPScore;
        private System.Windows.Forms.PictureBox pbxDScore;
        private System.Windows.Forms.PictureBox pbxD1;
        private System.Windows.Forms.PictureBox pbxD2;
        private System.Windows.Forms.PictureBox pbxD3;
        private System.Windows.Forms.PictureBox pbxD4;
        private System.Windows.Forms.PictureBox pbxD5;
        private System.Windows.Forms.PictureBox pbxD6;
        private System.Windows.Forms.PictureBox pbxD7;
        private System.Windows.Forms.PictureBox pbxD8;
        private System.Windows.Forms.PictureBox pbxP1;
        private System.Windows.Forms.PictureBox pbxP2;
        private System.Windows.Forms.PictureBox pbxP3;
        private System.Windows.Forms.PictureBox pbxP4;
        private System.Windows.Forms.PictureBox pbxP5;
        private System.Windows.Forms.PictureBox pbxP6;
        private System.Windows.Forms.PictureBox pbxP7;
        private System.Windows.Forms.PictureBox pbxP8;
        private System.Windows.Forms.PictureBox pbxD9;
        private System.Windows.Forms.PictureBox pbxD10;
        private System.Windows.Forms.PictureBox pbxD11;
        private System.Windows.Forms.PictureBox pbxP9;
        private System.Windows.Forms.PictureBox pbxP10;
        private System.Windows.Forms.PictureBox pbxP11;
        private System.Windows.Forms.Button btnDeal;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.Label lblWins;
        private System.Windows.Forms.Label lblTies;
        private System.Windows.Forms.Label lblLosses;
    }
}

